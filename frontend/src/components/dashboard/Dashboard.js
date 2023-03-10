import React, {useState} from "react"
import logoImage from "../../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import AllBooksPage from "../all-books-page/AllBooksPage";
import Tabs from "../tabs/Tabs";
import YourBooksPage from "../your-books-page/YourBooksPage";
import AvailableBooksPage from "../available-books-page/AvailableBooksPage";
import RentedBooksPage from "../rented-books-page/RentedBooksPage";
import { useNavigate } from "react-router-dom";
import  "./Dashboard.css"
import { useDispatch } from 'react-redux';
import { logoutUser } from "../../redux/reducers/userReducer";
import WaitingListPage from "../waiting-list/WaitingListPage";

const Dashboard = () => {
    const [searchInput, setSearchInput] = useState("");
    const dispatch = useDispatch();
    let navigate = useNavigate();

    const handleLogout = () => {
        routeChange();
        localStorage.removeItem('token');
        dispatch(logoutUser());
    }
    const routeChange = () => {
        let path = '/';
        navigate(path);
    }

    const handleSearch = () =>{
        navigate(`/searchedBooks/${searchInput}`);
    }

    return (
        <div>
            <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt="" onClick={()=>{navigate('/dashboard')}}/>
                <TextField
                    className="searchDiv"
                    label="Search books by title or author"
                    onChange={(e) => setSearchInput(e.target.value)}
                    InputProps={{
                        className: "searchBar",
                        endAdornment: (
                        <InputAdornment position="end" style={{"marginLeft": "15px"}}>
                            <IconButton onClick={handleSearch}>
                                <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                        )
                    }}
                />
                <button type="submit" className="logout_btn" onClick={handleLogout}>Logout</button>
            </div>
            <div>
                <Tabs>
                    <div label="All Books">
                        <AllBooksPage />
                    </div>
                    <div label="Your Books">
                        <YourBooksPage />
                    </div>
                    <div label="Available Books">
                        <AvailableBooksPage />
                    </div>
                    <div label="Rented Books">
                        <RentedBooksPage />
                    </div>
                    <div label="Waiting List">
                        <WaitingListPage />
                    </div>
                </Tabs>
            </div>  
        </div>
    );
}

export default Dashboard;