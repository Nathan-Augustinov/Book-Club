import React, { useEffect } from "react";
import Book from "../book/Book";
import "./AllBooksPage.css";
import { addAllBooks } from "../../redux/reducers/allBooksReducer";
import { useDispatch, useSelector } from "react-redux";
const AllBooksPage = () => {

    const token = localStorage.getItem('token');
    
    const fetchBooks = async () => {
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
    const dispatch = useDispatch();
    useEffect(()=>{

        const getBooks = async ()=>{
            const data = await fetchBooks();
            dispatch(addAllBooks(data));
        };

        getBooks();
    },[]);

    const books = useSelector(state => state.allBooks);
    

    return (
        <div className="bookslist">
            {/* {containers} */}
            {/* <div className="div_container">
                <div className="flex_child">
                    <Book />
                </div>
                <div className="flex_child">
                    <Book />
                </div>
            </div> */}
            {books.map(book => (
                <div key={book.bookId} className="flex_child">
                    <Book id={book.bookId}/>
                </div>
            ))}
            {/* {books.map(firstBook => (
                <div className="div_container">
                    <div className="flex_child">
                        <Book id={firstBook.bookId}/>
                    </div>
                </div>
            ))} */}
        </div>
    );
}

export default AllBooksPage;