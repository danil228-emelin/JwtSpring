# JwtSpring
The client—This is the application consuming the backend. It could be a mobile
app or the frontend of a web application developed using a framework like
Angular, ReactJS, or Vue.js.
The authentication server—This is an application with a database of user creden-
tials. The purpose of this application is to authenticate users based on their cre-
dentials (username and password) and send them a one-time password (OTP),
 OTP value is reading  from the database directly.
 The resource server -This is an application has resources (entry points).
Authentication server secures access to them.
Before calling an endpoint, the user must authenticate with their username and password and
then send an OTP. After OTP is send with request and if OTP is valid, server gives JWT token.
Token - is kind of access card.
