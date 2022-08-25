import React from 'react';
import { useNavigate } from "react-router-dom";
import { useSelector } from 'react-redux';
import "./Book.css";

const Book = ({id}) => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = `../bookpage/${id}`;
        navigate(path);
    }
    const books = useSelector(state => state.allBooks);
    return (
      <div className="book div_container" onClick={routeChange}>
        <div className="col-md-2 book_image flex_child">
          <img src={books.filter(book => book.bookId === id).map(book => book.image)} alt=""/>
        </div>
        <div className="col-md-8 book_details flex_child">
          <div className="book_title">
              <p><b>Title: </b>{books.filter(book => book.bookId === id).map(book => book.title)}</p>
          </div>
          <div className="book_author">
              <p><b>Author: </b>{books.filter(book => book.bookId === id).map(book => book.author)}</p>
          </div>
        </div>
      </div>
    );
}

export default Book;