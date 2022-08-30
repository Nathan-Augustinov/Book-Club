import React, {useEffect} from "react";
import Book from "../book/Book";
import "./RentedBooksPage.css";
import { useDispatch, useSelector } from "react-redux";
import { addAllRentedBooks } from "../../redux/reducers/rentedBooksReducer.js";
const RentedBooksPage = () => {
    const token = localStorage.getItem('token');
    const fetchBooks = async () => {
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
    const dispatch = useDispatch();
    useEffect(()=>{

        const getBooks = async ()=>{
            const data = await fetchBooks();
            dispatch(addAllRentedBooks(data));
        };

        getBooks();
    },[]);

    const rentedBooks = useSelector(state => state.rentedBooks);
    const user = useSelector(state => state.user);
    const userId = user.userId;
    return (
        <div className="bookslist">
            <div className="div_container">
                <div className="flex_child">
                    <h3 className="text">Books rented by you</h3>
                    <div className="wrapper">
                        {rentedBooks.filter(rentedBook => rentedBook.rentUser.userId === userId).map(rentedBook => (
                            <div key={rentedBook.forRentBook.usersBooks.book.bookId} className="flex_child">
                                <Book id={rentedBook.forRentBook.usersBooks.book.bookId}/>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="flex_child">
                    <h3 className="text">Your rented books</h3>
                    <div className="wrapper">
                        {rentedBooks.filter(rentedBook => rentedBook.forRentBook.usersBooks.user.userId === userId).map(rentedBook => (
                            <div key={rentedBook.forRentBook.usersBooks.book.bookId} className="flex_child">
                                <Book id={rentedBook.forRentBook.usersBooks.book.bookId}/>
                            </div>
                        ))}
                    </div>
                </div>
                
            </div>
        </div>
    );
}

export default RentedBooksPage;