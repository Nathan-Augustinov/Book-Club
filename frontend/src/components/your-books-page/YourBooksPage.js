import React, { useEffect, useState } from "react";
import Book from "../book/Book";
import "./YourBooksPage.css";
import { useDispatch, useSelector } from "react-redux";
import { addYourBooks } from "../../redux/reducers/yourBooksReducer";
import { useNavigate } from "react-router-dom";
const YourBooksPage = () => {

    const [title, setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [description, setDescription] = useState("");
    const [publishedDate, setPublishedDate] = useState(null);
    const [image, setImage] = useState("");

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
    const navigate = useNavigate();

    useEffect(()=>{

        const getBooks = async ()=>{
            const data = await fetchBooks();
             dispatch(addYourBooks(data));
        };

        getBooks();
    },[]);

    const resetForm = () => {
        setTitle("");
        setAuthor("");
        setDescription("");
        setPublishedDate(null);
        setImage("");
    };

    const addBook = async (values, image) =>{
        const formData = new FormData();
        formData.append("book", JSON.stringify(values));
        formData.append("image", image);
        const response = await fetch(`http://localhost:8080/api/books/addBookByUserId/${encodeURIComponent(userId)}`, {
            method: "POST",
            headers:{
                // 'Content-Type': 'multipart/form-data',
                // 'Accept': 'application/json',
                'Authorization': "Bearer " + token,
            },
            body: formData
        });

        if(response && !response.ok){
            const error = await response.text();
            alert(error);
            throw new Error(error);
        }
        
        return response;
    }

    const handleAddBook = () => {
        const values = {
            title: title,
            author: author,
            description: description,
            publishedDate: publishedDate
        };

        addBook(values, image)
            .then(() => {
                navigate("/dashboard");
                resetForm();
            })
            .catch(err => console.log(err));

    }

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
                                <input className="form_input" type="text" id="title" onChange={(event) => {setTitle(event.target.value)}}/>
                            </div>
                            <div className="author">
                                <label className="form_label">Author </label>
                                <input className="form_input" type="text" id="author" onChange={(event) => {setAuthor(event.target.value)}}/>
                            </div>
                            <div className="description">
                                <label className="form_label">Description </label>
                                <input className="form_input" type="email" id="description" onChange={(event) => {setDescription(event.target.value)}}/>
                            </div>
                            <div className="published_date">
                                <label className="form_label">Published date </label>
                                <input className="form_input" type="text" id="published_date" onChange={(event) => {setPublishedDate(event.target.value)}}/>
                            </div>
                            <div className="image">
                                <label className="form_label">Image </label>
                                <input className="form_input" type="file"  id="image" onChange={(event) => {setImage(event.target.files[0])}}/>
                            </div>
                        </div>
                        <div className="footer">
                            <button type="submit" className="btn" onClick={handleAddBook}>Add book</button>
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