import React, { useEffect, useState} from 'react';
import logoImage from "../../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import "./BookPage.css";
import { useDispatch, useSelector } from 'react-redux';
import { logoutUser } from "../../redux/reducers/userReducer";
import { useNavigate, useParams } from "react-router-dom";
import { bookSelected } from '../../redux/reducers/bookSelectedReducer';
import { addAllRentedBooks } from '../../redux/reducers/rentedBooksReducer';
import { addAvailableBooks } from '../../redux/reducers/availableBooksReducer';

const BookPage = () => {
    const [searchInput, setSearchInput] = useState("");
    const [rentOption, setRentOption] = useState("");
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

    const fetchRentedBooks = async () => {
        const response = await fetch('http://localhost:8080/api/rentedBooks', {
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

    const fetchAvailableForRentBooks = async () => {
        const response = await fetch('http://localhost:8080/api/forRentBooks/availableBooks', {
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
    }
    
    useEffect(()=>{
        const getBook = async ()=>{
            const data = await fetchBook();
            dispatch(bookSelected(data));
        };

        getBook();

        const getRentedBooks = async ()=>{
            const data = await fetchRentedBooks();
            dispatch(addAllRentedBooks(data));
        };

        getRentedBooks();

        const getAvailableBooks = async ()=>{
            const data = await fetchAvailableForRentBooks();
            dispatch(addAvailableBooks(data));
        };

        getAvailableBooks();


    },[])

    const selectedBook = useSelector(state => state.bookSelected);
    const user = useSelector(state => state.user);
    const userId = user ? user.userId : null;
    const rentedBooks = useSelector(state => state.rentedBooks);
    const searchedBooks = useSelector(state => state.searchedBooks);
    const availableBooks = useSelector(state => state.availableBooks);


    const handleSearch = () =>{
        navigate(`/searchedBooks/${searchInput}`);
    }

    const rentOptions = ["One week", "Two weeks", "Three weeks", "One month"];
    const extendRentOptions = ["One week", "Two weeks"];
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
                    {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === parseInt(id, 10) 
                                        && rentedBook.rentUser.userId === userId )
                                .map(rentedBook => (
                                    <div key={rentedBook.rentedBookId} className="book_return_date">
                                        <p><b>Book's return date: </b>{rentedBook ? rentedBook.returnDate : ""}</p>
                                    </div>
                    ))}
                    {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === parseInt(id, 10) 
                                        && rentedBook.rentUser.userId !== userId 
                                        && rentedBook.forRentBook.usersBooks.user.userId === userId)
                                .map(rentedBook => (
                                    <div key={rentedBook.rentedBookId} className="book_return_date">
                                        <p><b>Book's return date: </b>{rentedBook ? rentedBook.returnDate : ""}</p>
                                    </div>
                    ))}
                    {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === parseInt(id, 10) 
                                        && rentedBook.rentUser.userId !== userId 
                                        && rentedBook.forRentBook.usersBooks.user.userId === userId)
                                .map(rentedBook => (
                                    <div key={rentedBook.rentedBookId} className="book_return_date">
                                        <p><b>Book's renting user: </b>{rentedBook ? rentedBook.rentUser.username : ""}</p>
                                    </div>
                    ))}
                    {searchedBooks.filter(searchedBook => searchedBook.bookId === parseInt(id, 10))
                                    .map(searchedBook => availableBooks.filter(availableBook => availableBook.usersBooks.book.bookId === searchedBook.bookId).map(availableBook => (
                                        <div key={availableBook.forRentBookId} className="book_availability">
                                            <p><b>Book's availability: </b>Available</p>
                                        </div>
                                    )))}
                    {searchedBooks.filter(searchedBook => searchedBook.bookId === parseInt(id, 10))
                                    .map(searchedBook => rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === searchedBook.bookId).map(rentedBook => (
                                        <div key={rentedBook.rentedBookId} className="book_availability">
                                            <p><b>Book's availability: </b>{rentedBook.returnDate}</p>
                                        </div>
                                    )))}
                    {availableBooks.filter(book => book.usersBooks.book.bookId ===  parseInt(id, 10) 
                                            && book.usersBooks.user.userId !== userId)
                                    .map(book => (
                        <div className='rent'>
                            <div className='rent_options'>
                                {rentOptions.map((item, index) => (
                                    <div key={index}>
                                        <input value={item} type="checkbox"/>
                                        <span>{item}</span>
                                    </div>
                                ))}
                            </div>
                            <div>
                                <button type="submit" className="btn">Rent</button>
                            </div>
                        </div>     
                    ))}  

                    {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === parseInt(id, 10) 
                                        && rentedBook.rentUser.userId === userId)
                                .map(book => (
                        <div className='rent'>
                            <div className='rent_options'>
                                {extendRentOptions.map((item, index) => (
                                    <div key={index}>
                                        <input value={item} type="checkbox"/>
                                        <span>{item}</span>
                                    </div>
                                ))}
                            </div>
                            <div>
                                <button type="submit" className="btn">Extend rent</button>
                            </div>
                        </div>     
                    ))}

                    {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.book.bookId === parseInt(id, 10) 
                                        && rentedBook.rentUser.userId !== userId
                                        && rentedBook.forRentBook.usersBooks.user.userId !== userId)
                                .map(book => (
                        <div className='rent'>
                            <div>
                                <button type="submit" className="btn">Add yourself on the waiting list</button>
                            </div>
                        </div>     
                    ))}


                      
                </div> 
            </div>
            
        </div>
        
    );
}

export default BookPage;