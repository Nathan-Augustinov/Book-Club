import React, { useEffect } from "react";
import Book from "../book/Book";
import "./YourBooksPage.css";
import { useDispatch, useSelector } from "react-redux";
import { addYourBooks } from "../../redux/reducers/yourBooksReducer";
const YourBooksPage = () => {
    const token = localStorage.getItem('token');
    const user = useSelector(state => state.user);
    const userId = user.userId;
    const fetchBooks = async () => {
        const response = await fetch(`http://localhost:8080/api/books/getBooksByUserId/${encodeURIComponent(userId)}`, {
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
             dispatch(addYourBooks(data));
        };

        getBooks();
    },[]);

    const yourBooks = useSelector(state => state.yourBooks);

    return (
        <div className="bookslist">
            <button type="submit" className="btn">Add new book</button>
             <div className="wrapper">
                {yourBooks.map(book => (
                    <div key={book.bookId} className="flex_child">
                        <Book id={book.bookId}/>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default YourBooksPage;