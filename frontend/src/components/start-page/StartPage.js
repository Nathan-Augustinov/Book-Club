import React from "react";
import { useState } from "react";
import logoImage from "../../resources/logo.png"
import bookshelfImage from "../../resources/bookshelf.jpg"
import { useNavigate } from "react-router-dom";
import "./StartPage.css"

const StartPage = () => {
    let navigate = useNavigate();
    const routeChange = () => {
        let path = 'login';
        navigate(path);
    }
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const resetForm = () => {
        setFirstname("");
        setLastname("");
        setEmail("");
        setUsername("");
        setPassword("");
        setConfirmPassword("");
    };

    const Register = async (values) => {
        const response = await fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            headers:{
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(values)
        });

        if(response && !response.ok){
            const error = await response.text();
            alert(error);
            throw new Error(error);
        }
        
        return response;
    }

    async function handleSubmit(){
        if(password !== confirmPassword){
            alert("Passwords do not match");
            return;
        }

        const values = {
            firstname: firstname,
            lastname: lastname,
            email: email,
            username: username,
            password: password
        };

        Register(values)
            .then(() => {
                resetForm();
                routeChange();
            })
            .catch(err => {
                console.log(err);
            });
    }
    return (
        <div>
            <div className="startPageHeader">
                <img className="logoImage" src={logoImage} alt=""/>
            </div>
            <div>
                <div className="div_container">
                    <div className="flex_child">
                        <div className="register_form">
                            <p className="signUpMessage">Sign up to TMD Book Club</p>
                            <div className="form_body">
                                <div className="firstname">
                                    <label className="form_label">First Name </label>
                                    <input className="form_input" type="text" id="firstName" onChange={(event) => {setFirstname(event.target.value)}}/>
                                </div>
                                <div className="lastname">
                                    <label className="form_label">Last Name </label>
                                    <input  type="text" id="lastName"  className="form_input" onChange={(event) => {setLastname(event.target.value)}}/>
                                </div>
                                <div className="email">
                                    <label className="form_label">Email </label>
                                    <input  type="email" id="email" className="form_input" onChange={(event) => {setEmail(event.target.value)}}/>
                                </div>
                                <div className="username">
                                    <label className="form_label">Username </label>
                                    <input  type="text" id="username" className="form_input" onChange={(event) => {setUsername(event.target.value)}}/>
                                </div>
                                <div className="password">
                                    <label className="form_label">Password </label>
                                    <input className="form_input" type="password"  id="password" onChange={(event) => {setPassword(event.target.value)}}/>
                                </div>
                                <div className="confirm_password">
                                    <label className="form_label">Confirm Password </label>
                                    <input className="form_input" type="password" id="confirmPassword" onChange={(event) => {setConfirmPassword(event.target.value)}}/>
                                </div>
                            </div>
                            <div className="footer">
                                <button type="submit" className="btn" onClick={handleSubmit}>Sign Up</button>
                            </div>
                            <div>
                                <p>Already have an account?</p>
                                <button type="submit" className="btn" onClick={routeChange}>Login</button>
                            </div>
                        </div> 
                    </div>   
                    <div className="image_div flex_child">
                        <img className="bookshelfImage" src={bookshelfImage} alt=''/>
                    </div>
                </div>
            </div>
        </div>
        
    )
}

export default StartPage;
