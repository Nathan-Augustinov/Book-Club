import React, {useEffect} from "react";
import Book from "../book/Book";
import "./WaitingListPage.css";
import { useDispatch, useSelector } from "react-redux";
import { addWaitingListBooks } from "../../redux/reducers/waitingListReducer";
const WaitingListPage = () => {
    const token = localStorage.getItem('token');
    const fetchBooks = async () => {
        const response = await fetch('http://localhost:8080/api/waitingList', {
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
            dispatch(addWaitingListBooks(data));
        };

        getBooks();
    },[]);

    const waitingListBooks = useSelector(state => state.waitingList);
    const user = useSelector(state => state.user);
    const userId = user.userId;

    return (
        <div className="bookslist">
            <div className="wrapper">
                {waitingListBooks.filter(book => book.user.userId === userId).map(book => (
                    <div key={book.waitingListId} className="flex_child">
                        <Book id={book.usersBooks.book.bookId}/>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default WaitingListPage;