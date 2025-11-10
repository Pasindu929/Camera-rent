# Camera Rent Application

A full-stack camera rental application with JWT authentication, built with Spring Boot backend and React frontend.

## Features

- **User Authentication**: Secure login and signup with JWT tokens
- **Protected Routes**: Dashboard accessible only to authenticated users
- **Real-time Data**: Dashboard displays camera rental statistics
- **Responsive Design**: Modern UI with Tailwind CSS
- **Session Management**: Automatic token handling and logout functionality

## Tech Stack

### Backend
- Spring Boot 3.3.3
- Spring Security with JWT
- MongoDB for data storage
- Java 17

### Frontend
- React 18
- React Router for navigation
- Tailwind CSS for styling
- Context API for state management

## Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- MongoDB (running on localhost:27017)
- Maven 3.6+

## Getting Started

### 1. Start MongoDB

Make sure MongoDB is running on your system:
```bash
# Windows
mongod

# macOS/Linux
sudo systemctl start mongod
```

### 2. Backend Setup

Navigate to the backend directory:
```bash
cd backend-spring
```

Install dependencies and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:5000`

### 3. Frontend Setup

Navigate to the frontend directory:
```bash
cd frontend
```

Install dependencies:
```bash
npm install
```

Start the development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## Testing the Authentication Flow

### 1. Create a New Account
1. Open `http://localhost:3000`
2. Click "Sign Up" or navigate to `/signup`
3. Enter a username/email and password (minimum 6 characters)
4. Click "Sign Up"
5. You should be redirected to the dashboard with a success message

### 2. Login
1. Navigate to `/login`
2. Enter your credentials
3. Click "Sign In"
4. You should be redirected to the dashboard

### 3. Access Protected Routes
1. While logged in, you can access `/dashboard`
2. Try logging out and accessing `/dashboard` directly - you should be redirected to login

### 4. Test Logout
1. Click the "Logout" button in the navbar or dashboard
2. You should be redirected to the home page
3. Try accessing `/dashboard` again - you should be redirected to login

## API Endpoints

### Authentication
- `POST /api/auth/signup` - Create new user account
- `POST /api/auth/login` - User login
- `POST /api/auth/logout` - User logout (client-side)
- `POST /api/auth/verify` - Verify JWT token

### Protected Routes
- `GET /api/dashboard` - Get dashboard data (requires authentication)

## Project Structure

```
├── backend-spring/
│   ├── src/main/java/com/camerarent/
│   │   ├── auth/           # Authentication controllers and JWT utilities
│   │   ├── config/         # Security configuration
│   │   ├── controller/     # API controllers
│   │   └── user/          # User model and services
│   └── pom.xml            # Maven dependencies
├── frontend/
│   ├── src/
│   │   ├── components/    # React components
│   │   ├── contexts/      # Authentication context
│   │   └── App.js         # Main application component
│   └── package.json       # NPM dependencies
└── README.md
```

## Security Features

- **JWT Tokens**: Secure authentication with 24-hour expiration
- **Password Hashing**: BCrypt encryption for stored passwords
- **Protected Routes**: Server-side and client-side route protection
- **CORS Configuration**: Proper cross-origin resource sharing setup
- **Input Validation**: Server-side validation for all user inputs

## Troubleshooting

### Backend Issues
- Ensure MongoDB is running on port 27017
- Check that Java 17+ is installed
- Verify Maven dependencies are properly installed

### Frontend Issues
- Clear browser cache and localStorage if authentication seems stuck
- Check browser console for any JavaScript errors
- Ensure the backend is running on port 5000

### Database Issues
- MongoDB should be running and accessible
- The database name is `camerarent`
- Users are stored in the `users` collection

## Development Notes

- JWT secret key is hardcoded for development (should use environment variables in production)
- CORS is configured to allow requests from `localhost:3000`
- All authentication routes are publicly accessible
- Dashboard and other protected routes require valid JWT tokens

## Next Steps

To extend this application, consider adding:
- User profile management
- Camera inventory management
- Booking and rental functionality
- Payment integration
- Admin dashboard
- Email notifications
- Image upload for cameras
