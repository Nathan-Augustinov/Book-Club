import React from "react"
import logoImage from "../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import AllBooksPage from "./AllBooksPage";
import Tabs from "./Tabs";
import YourBooksPage from "./YourBooksPage";
import AvailableBooksPage from "./AvailableBooksPage";
import RentedBooksPage from "./RentedBooksPage";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
    let navigate = useNavigate();
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
                        <InputAdornment>
                            <IconButton>
                            <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                        )
                    }}
                />
                <button type="submit" className="logout_btn" onClick={routeChange}>Logout</button>
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