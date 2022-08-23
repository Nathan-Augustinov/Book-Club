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
            <div className="div_container">
                {/* <button type="submit" className="btn">Add new book</button> */}
                <div className="flex_child">
                    <div className="register_form">
                        <p className="addBookMessage">Add a new book for rent</p>
                        <div className="form_body">
                            <div className="title">
                                <label className="form_label">Title </label>
                                <input className="form_input" type="text" id="title"/>
                            </div>
                            <div className="author">
                                <label className="form_label">Author </label>
                                <input className="form_input" type="text" id="author" />
                            </div>
                            <div className="description">
                                <label className="form_label">Description </label>
                                <input className="form_input" type="email" id="description" />
                            </div>
                            <div className="published_date">
                                <label className="form_label">Published date </label>
                                <input className="form_input" type="text" id="published_date" />
                            </div>
                            <div className="image">
                                <label className="form_label">Image </label>
                                <input className="form_input" type="file"  id="image"/>
                            </div>
                        </div>
                        <div className="footer">
                            <button type="submit" className="btn">Add book</button>
                        </div>
                    </div>
                </div> 
                <div className="flex_child">
                    <div className="wrapper">
                        {yourBooks.map(book => (
                            <div key={book.bookId} className="flex_child">
                                <Book id={book.bookId}/>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default YourBooksPage;