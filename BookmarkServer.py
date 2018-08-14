import http.server
import requests
from urllib.parse import unquote, parse_qs
from http.cookies import SimpleCookie
import cgi
import MySQLdb
from http import cookies
import hashlib

memory = {}
salt="check"
form = '''<!DOCTYPE html>
<head>
    <title>Login</title>
</head>
<body>
<h1 style="margin-left:40%;margin-top:20px;">Login User</h1>
<form method="POST" style="margin-top:100px;margin-left:33%;">
    
        <input style="width: 300px;" type="text" name ="user"   placeholder="Enter User Name" maxlength="25" required>
   
    <br>
    <br>
    <br>
        <input  type="password" name ="pass"   placeholder="Enter Password" required style="width: 300px;">
   
    <br>
    <input style="color:red;margin-left:22%;font-size:25px;margin-top:20px;" type="submit" name="sub" value="Login">
</form>
</body>
'''

detail = '''<!DOCTYPE html>
<head>
    <title>User Details</title>
</head>
<body>
    <h1 style="margin-left:40%;margin-top:20px;">User Details</h1>
    <table style="margin-left:300px;">
        <tr style="margin-left:80px;">
            <th style="margin-left:80px;font-size:20px">Firstname</th>
            <th style="margin-left:80px;font-size:20px">LastName</th>
            <th style="margin-left:80px;font-size:20px">Aadhar ID</th>
            <th style="margin-left:80px;font-size:20px">Gender</th>
            <th style="margin-left:80px;font-size:20px">Email ID</th>
        </tr>
        {}
    </table>
</body>
'''
users = []
def readdata():
    conn = MySQLdb.connect("localhost","root","","test")
    sql = conn.cursor();
    sql.execute("SELECT * FROM emp_records ")
    for row in sql.fetchall():
        list=(row[0], row[1], row[3], row[4], row[5])
        users.append(list)
def logincheck(user):
    conn = MySQLdb.connect("localhost","root","","test" )
    sql=conn.cursor();
    sql.execute("SELECT * FROM emp_records where username='{}'".format(user))   
    for row in sql.fetchall():
        if row[6]:
            return True
    return False
def login(user, password):
    conn = MySQLdb.connect("localhost","root","","test" )
    sql=conn.cursor();
    sql.execute("SELECT * FROM emp_records where username='{}' and password='{}'".format(user,password))   
    for row in sql.fetchall():
        if row[6] and row[7]:
            return True
    return False


class Shortener(http.server.BaseHTTPRequestHandler):
    def do_POST(self):
        length = int(self.headers.get('Content-length', 0))
        body = self.rfile.read(length).decode()
        params = parse_qs(body)
        user = params["user"][0]
        password = params["pass"][0]
        check = login(user, password)
        if(check):
            self.send_response(303)
            self.send_header('Location', '/detail')
            cookie_object = cookies.SimpleCookie()
            cookie_object['user'] = user
            cookie_object['user']['domain'] = 'localhost'
            cookie_object['user']['max-age'] = 10000
            hash_object = hashlib.sha256()
            hash_object.update((user + salt).encode())
            cookie_object["hash"] = hash_object.hexdigest()
            cookie_object['hash']['domain'] = 'localhost'
            cookie_object['hash']['max-age'] = 10000
            self.send_header('Set-Cookie', 
                cookie_object["hash"].OutputString())
            self.send_header('Set-Cookie', 
                cookie_object['user'].OutputString())
            self.end_headers()
        else:
            self.send_response(404)
            self.end_headers()
            self.wfile.write('Enter valid user id and password'.encode())

    def do_GET(self):
        form_name = unquote(self.path[1:])
        
        def validUser():
            cookie_header = self.headers.get('Cookie')
            cookie = SimpleCookie(cookie_header)
            m = hashlib.sha256()
            try:
                if cookie["user"] and cookie["hash"]:
                    user = cookie["user"].value
                    hash_value = cookie["hash"].value
                    temp_hash = (user+salt).encode()
                    m.update(temp_hash)
                    temp_hexdigest = m.hexdigest()
                    if hash_value == temp_hexdigest:
                        return True
                    else:
                        return False
            except KeyError:
                self.send_response(303)
                self.send_header('Location','/form')
                self.end_headers()

        if form_name:
            if (form_name == "detail"):
                if validUser():
                    self.send_response(200)
                    self.send_header('Location', '/detail')
                    self.end_headers()
                    form_name = "\n".join("<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>".format(row[0],row[1],row[2],row[3],row[4])
                                  for row in users)
                    self.wfile.write(detail.format(form_name).encode())
                    
                else:
                    self.send_response(404)
                    self.send_header('Content-type', 'text/plain; charset=utf-8')
                    self.end_headers()
                    self.wfile.write("Login plz".encode())
            elif (form_name == 'form'):
                
                self.send_response(200)
                self.send_header('Location', '/form')
                self.end_headers()
                self.wfile.write(form.encode())

            else:
                self.send_response(404)
                self.send_header('Content-type', 'text/plain; charset=utf-8')
                self.end_headers()
                self.wfile.write("I don't know '{}'.".format(form_name).encode())
        else:
            self.send_response(200)
            self.send_header('Location', '/')
            self.end_headers()
            known = "\n".join("{} : {}".format(key, memory[key])
                              for key in sorted(memory.keys()))
            self.wfile.write(form.format(known).encode())
            


if __name__ == '__main__':
    server_address = ('', 8092)
    readdata()
    httpd = http.server.HTTPServer(server_address, Shortener)
    httpd.serve_forever()
