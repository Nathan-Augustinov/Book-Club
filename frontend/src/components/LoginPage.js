import React from "react"
import logoImage from "../resources/logo.png"
import bookshelfImage_part1 from "../resources/bookshelf_part1.png"
import bookshelfImage_part2 from "../resources/bookshelf_part2.png"

const LoginPage = () => {
    return (
        <div>
            <div className="startPageHeader">
                <img className="logoImage" src={logoImage} alt=""/>
            </div>
            <div className="div_container">
                <div className="flex_child">
                    <img className="bookshelfImage" src={bookshelfImage_part1} alt=''/>
                </div>
                <div className="flex_child">
                    <div className="login_form">
                        <p className="LoginMessage">Log into your TMD Book Club account</p>
                        <div className="form_body">
                            <div className="username">
                                <label className="form_label" for="username">Username </label>
                                <input  type="text" id="username" className="form_input"/>
                            </div>
                            <div className="password">
                                <label className="form_label" for="password">Password </label>
                                <input className="form_input" type="password"  id="password"/>
                            </div>
                        </div>
                        <div class="footer">
                            <button type="submit" class="btn">Login</button>
                        </div>
                    </div> 
                </div>
                <div className="flex_child">
                    <img className="bookshelfImage" src={bookshelfImage_part2} alt=''/>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;