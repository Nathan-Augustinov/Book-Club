import './App.css';
import {Routes, Route} from "react-router-dom";
import StartPage from './components/start-page/StartPage';
import LoginPage from './components/login-page/LoginPage'; 
import Dashboard from './components/dashboard/Dashboard';
import BookPage from './components/book-page/BookPage';
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";
import { useEffect } from 'react';
import { updateUser } from './redux/reducers/userReducer';

function App() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const routeChange = () =>{
    let path="/login";
    navigate(path);
  }

  const token = localStorage.getItem("token");

  const verifyToken = async (token) => {
      const response = await fetch(`http://localhost:8080/api/users/verifyToken?token=${encodeURIComponent(token)}`,{
        method: "GET",
        headers:{
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    });

    if(response && !response.ok){
        const error = await response.text();
        throw new Error(error);
    }

    return response.json();
  }

  useEffect(() => {
    if(token){
        verifyToken(token)
          .then(response => {
            dispatch(updateUser(response));
          })
          .catch(err => {
            localStorage.removeItem("token");
            routeChange();
          })
    }
  });

  return (
        <Routes>
          <Route path="/" element={<StartPage/>} />
          <Route path="/login" element={<LoginPage/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
          <Route path="/bookpage" element={<BookPage/>} />
        </Routes>
  );
}

export default App;
