import React, {useEffect} from "react";
import Book from "../book/Book";
import "./AvailableBooksPage.css";
import { useDispatch, useSelector } from "react-redux";
import { addAvailableBooks } from "../../redux/reducers/availableBooksReducer";
const AvailableBooksPage = () => {
    const token = localStorage.getItem('token');
    const fetchBooks = async () => {
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
    };
    const dispatch = useDispatch();
    useEffect(()=>{

        const getBooks = async ()=>{
            const data = await fetchBooks();
            dispatch(addAvailableBooks(data));
        };

        getBooks();
    },[]);

    const availableBooks = useSelector(state => state.availableBooks);

    return (
        <div className="bookslist">
            <div className="wrapper">
                {availableBooks.map(availableBook => (
                    <div key={availableBook.usersBooks.book.bookId} className="flex_child">
                        <Book id={availableBook.usersBooks.book.bookId}/>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default AvailableBooksPage;