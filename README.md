Authentication Flow
This project implements a secure user authentication process using JWT (JSON Web Tokens) to authenticate and authorize users. Below is a high-level overview of the authentication steps, including password storage and token usage.

1. Password Stored in Database (Encoded)
User passwords are securely stored in the database by encoding them. This ensures sensitive information is never stored in plaintext, adding an extra layer of security.

![image](https://github.com/user-attachments/assets/0d4d64e0-58ff-47be-b0a1-1b113bb719eb)

2. Authenticated User Receives JWT Token
Upon successful login, an authenticated user is issued a JWT Token. This token contains user information and is used to verify the identity of the user in subsequent requests.

![image](https://github.com/user-attachments/assets/22e0f2f0-5afb-49d4-b6d1-f62e78447576)


3. Accessing Endpoints with JWT Token
Once a user has received the JWT token, they can use it to access protected endpoints. The token is sent along with the request in the Authorization header. This token is validated to ensure the user is authorized to access the requested resource.

![image](https://github.com/user-attachments/assets/f3e3ec1a-8dbb-405d-934f-f051569fccee)



