import React from "react"
import logoImage from "../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
// import Box from '@mui/material/Box';
// import Tabs from '@mui/material/Tabs';
// import Tab from '@mui/material/Tab';
import AllBooksPage from "./AllBooksPage";
import Tabs from "./Tabs";
import YourBooksPage from "./YourBooksPage";
import AvailableBooksPage from "./AvailableBooksPage";
import RentedBooksPage from "./RentedBooksPage";

const Dashboard = () => {
    // const [value, setValue] = React.useState(0);

    // const handleChange = (event, newValue) => {
    //     setValue(newValue);
    // };

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
                <button type="submit" className="logout_btn">Logout</button>
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