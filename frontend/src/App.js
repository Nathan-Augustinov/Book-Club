import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import StartPage from './components/start-page/StartPage';
import LoginPage from './components/login-page/LoginPage'; 
import Dashboard from './components/dashboard/Dashboard';
import BookPage from './components/book-page/BookPage';

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<StartPage/>} />
          <Route path="/login" element={<LoginPage/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
          <Route path="/bookpage" element={<BookPage/>} />
        </Routes>
    </Router>
  );
}

export default App;
