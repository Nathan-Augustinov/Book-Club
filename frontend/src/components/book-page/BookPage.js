import React, { useEffect, useState} from 'react';
import logoImage from "../../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import "./BookPage.css";
import { useDispatch, useSelector } from 'react-redux';
import { logoutUser } from "../../redux/reducers/userReducer";
import { useNavigate, useParams } from "react-router-dom";
import { bookSelected } from '../../redux/reducers/bookSelectedReducer';

const BookPage = () => {
    const [searchInput, setSearchInput] = useState("");
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
    const {id} = useParams();
    const token = localStorage.getItem('token');

    const fetchBook = async () => {
        const response = await fetch(`http://localhost:8080/api/books/${encodeURIComponent(id)}`, {
            method: "GET",
            headers:{
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': "Bearer " + token,
            },
        });

        if(response && !response.ok){
            const error = await response.text();
            throw new Error(error);
        }

        return response.json();
    };
    useEffect(()=>{
        const getBook = async ()=>{
            const data = await fetchBook();
            console.log(data.publishedDate);
            dispatch(bookSelected(data));
        };

        getBook();
    },[])

    const selectedBook = useSelector(state => state.bookSelected);

    const handleSearch = () =>{
        navigate(`/searchedBooks/${searchInput}`);
    }
    return (
        <div>
           <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt="" onClick={()=>{navigate('/dashboard')}}/>
                <TextField
                    className="searchDiv"
                    label="Search books by title or author"
                    onChange={(e) => setSearchInput(e.target.value)}
                    InputProps={{
                        className: "searchBar",
                        endAdornment: (
                        <InputAdornment position="end" style={{"marginLeft": "15px"}}>
                            <IconButton onClick={handleSearch}>
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
                    <img src={selectedBook ? selectedBook.image : null} alt=""/>
                </div>
                <div className="col-md-6 bookpage_details flex_child">
                    <div className="book_title">
                        <p><b>Title: </b>{selectedBook ? selectedBook.title : ""}</p>
                    </div>
                    <div className="book_author">
                        <p><b>Author: </b>{selectedBook ? selectedBook.author : ""}</p>
                    </div>
                    <div className="book_description">
                        <p><b>Book's description: </b>{selectedBook ? selectedBook.description : ""}</p>
                    </div>
                    <div className="book_published_date">
                        <p><b>Book's published date: </b>{selectedBook ? selectedBook.publishedDate : ""}</p>
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