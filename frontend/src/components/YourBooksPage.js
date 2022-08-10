import React from "react";
import Book from "./Book";
const YourBooksPage = () => {
    return (
        <div className="container bookslist">
            <button type="submit" className="btn">Add new book</button>
            <div className="div_container">
                <div className="flex_child">
                    <Book />
                </div>
                <div className="flex_child">
                    <Book />
                </div>
            </div>
            <div className="div_container">
                <div className="flex_child">
                    <Book />
                </div>
                <div className="flex_child">
                    <Book/>
                </div>
            </div>
            <div className="div_container">
                <div className="flex_child">
                    <Book />
                </div>
                <div className="flex_child">
                    <Book />
                </div>
            </div>
            <div className="div_container">
                <div className="flex_child">
                    <Book />
                </div>
                <div className="flex_child">
                    <Book />
                </div>
            </div>
        </div>
    );
}

export default YourBooksPage;