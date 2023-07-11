# JwtSpring
The client—This is the application consuming the backend. It could be a mobile
app or the frontend of a web application developed using a framework like
Angular, ReactJS, or Vue.js. We don’t implement the client part of the system,
but keep in mind that it exists in a real-world application. Instead of using the
client to call endpoints, we use cURL.
The authentication server—This is an application with a database of user creden-
tials. The purpose of this application is to authenticate users based on their cre-
dentials (username and password) and send them a one-time password (OTP),
 OTP value is reading  from the database directly.
