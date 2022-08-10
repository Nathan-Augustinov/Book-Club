import React from "react";
import Book from "./Book";
const AvailableBooksPage = () => {
    return (
        <div className="container bookslist">
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

export default AvailableBooksPage;