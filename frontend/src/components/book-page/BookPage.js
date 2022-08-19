import React from 'react';
import logoImage from "../../resources/logo.png"
import bookshelfImage from "../../resources/bookshelf.jpg"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import "./BookPage.css";
import { useDispatch } from 'react-redux';
import { logoutUser } from "../../redux/reducers/userReducer";
import { useNavigate } from "react-router-dom";

const BookPage = () => {
    const dispatch = useDispatch();
    let navigate = useNavigate();
    const handleLogout = () => {
        routeChange();
        localStorage.removeItem('token');
        dispatch(logoutUser());
    }
    const routeChange = () => {
        let path = '/';
        navigate(path);
    }
    return (
        <div>
           <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt=""/>
                <TextField
                    className="searchDiv"
                    label="Search books"
                    InputProps={{
                        className: "searchBar",
                        endAdornment: (
                        <InputAdornment position='start'>
                            <IconButton>
                            <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                        )
                    }}
                />
                <button type="submit" className="logout_btn" onClick={handleLogout}>Logout</button>
            </div>
            <div className="book div_container">
                <div className="col-md-4 bookpage_image flex_child ">
                    <img src={bookshelfImage} alt=""/>
                </div>
                <div className="col-md-6 bookpage_details flex_child">
                    <div className="book_title">
                        <p><b>Title: </b>Baltagul</p>
                    </div>
                    <div className="book_author">
                        <p><b>Author: </b>Mihail Sadoveanu</p>
                    </div>
                    <div className="book_description">
                        <p><b>Book's description: </b>Description</p>
                    </div>
                    <div className="book_published_date">
                        <p><b>Book's published date: </b>09.08.2022</p>
                    </div>
                    <div className='div_container'>
                        <div className='flex_child'>
                            <button type="submit" className="btn">Rent</button>
                        </div>
                        {/* <div className='flex_child'>
                            <button type="submit" className="btn">Extend rent period</button>
                        </div> */}
                    </div>       
                </div> 
            </div>
            
        </div>
        
    );
}

export default BookPage;