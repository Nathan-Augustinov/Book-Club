import React, { useEffect } from "react";
import Book from "../book/Book";
import "./AllBooksPage.css";
import { addAllBooks } from "../../redux/reducers/allBooksReducer";
import { useDispatch } from "react-redux";
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
            //alert(error);
            throw new Error(error);
        }

        return response.json();
    };
    const dispatch = useDispatch();
    useEffect(()=>{
        // getBooks()
        //     .then(res => {
        //         console.log(res);
        //         dispatch(addAllBooks(res));
        //     })
        //     .catch(err => console.log(err));

        const getBooks = async ()=>{
            const data = await fetchBooks();
            dispatch(addAllBooks(data));
        };

        getBooks();
    },[]);

    return (
        <div className="bookslist">
                <div className="div_container">
                    <div className="flex_child">
                        <Book />
                    </div>
                    <div className="flex_child">
                        <Book />
                    </div>
                </div>
                {/* <div className="div_container">
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
                </div> */}
            </div>
    );
}

export default AllBooksPage;