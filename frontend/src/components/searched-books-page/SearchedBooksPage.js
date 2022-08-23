import React, {useEffect, useState} from "react"
import logoImage from "../../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from "react-router-dom";
import { logoutUser } from "../../redux/reducers/userReducer";
import { addSearchedBooks } from "../../redux/reducers/searchBooksReducer";
import { addAllBooks } from "../../redux/reducers/allBooksReducer";
import Book from "../book/Book";
import "./SearchedBooksPage.css"

const SearchedBooksPage = () => {
    const [searchText, setSearchText] = useState("");
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
    const params = useParams();
    const token = localStorage.getItem("token");

    const fetchSearchedBooks = async (query) => {
        const response = await fetch(`http://localhost:8080/api/books/titleOrAuthor/${encodeURIComponent(query)}`, {
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

    const fetchAllBooks = async () => {
        const response = await fetch("http://localhost:8080/api/books", {
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
        const getSearchedBooks = async (query)=>{
            const data = await fetchSearchedBooks(query);
            dispatch(addSearchedBooks(data));
        };
        const {searchInput} = params;
        getSearchedBooks(searchInput);

        const getAllBooks = async () => {
            const data = await fetchAllBooks();
            dispatch(addAllBooks(data));
        }
        getAllBooks();
    },[params])

    const searchedBooks = useSelector(state => state.searchedBooks);

    const handleSearch = () =>{
        navigate(`/searchedBooks/${searchText}`);
    }

    return (
        <div>
            <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt="" onClick={()=>{navigate('/dashboard')}}/>
                <TextField
                    className="searchDiv"
                    label="Search books by title or author"
                    onChange={(e) => setSearchText(e.target.value)}
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
            <div className="bookslist">
                <div className="wrapper">
                    {searchedBooks.map(book => (
                        <div key={book.bookId} className="flex_child">
                            <Book id={book.bookId}/>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
    
}

export default SearchedBooksPage;