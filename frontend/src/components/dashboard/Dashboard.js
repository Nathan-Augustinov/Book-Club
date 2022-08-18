import React from "react"
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

const Dashboard = () => {
    const dispatch = useDispatch();
    let navigate = useNavigate();
    const handleLogout = () => {
        routeChange();
        dispatch(logoutUser());
    }
    const routeChange = () => {
        let path = '/';
        navigate(path);
    }

    return (
        <div>
            <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt=""/>
                <TextField
                    className="searchDiv"
                    label="Search books"
                    InputProps={{
                        className: "searchBar",
                        endAdornment: (
                        <InputAdornment position="start">
                            <IconButton>
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
                </Tabs>
            </div>  
        </div>
    );
}

export default Dashboard;