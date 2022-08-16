import React from 'react';
import bookshelfImage from "../../resources/bookshelf.jpg"
import { useNavigate } from "react-router-dom";
import "./Book.css";

const Book = () => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = '../bookpage';
        navigate(path);
    }

    return (
      <div className="book div_container" onClick={routeChange}>
        <div className="col-md-2 book_image flex_child">
          <img src={bookshelfImage} alt="" height="150" />
        </div>
        <div className="col-md-8 book_details flex_child">
          <div className="book_title">
              <p><b>Title: </b>Baltagul</p>
          </div>
          <div className="book_author">
              <p><b>Author: </b>Mihail Sadoveanu</p>
          </div>
        </div>
      </div>
    );
}

export default Book;