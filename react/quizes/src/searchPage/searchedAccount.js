import React from 'react';
import './searchedAccount.css'

export function searchedAccount(data, page, i) {
    var username = data.username;
    var name = data.firstname + " " + data.secondname;
    var parent = page;
    var key = i;
    console.log(key);
    return (
        <div id="srch-top-box" key={key}>
            <div id="srchAccB1">
                <div>
                    <img id="srchAccImage" src="http://localhost:8080/images/Profile/giorgi.jpg" width="100" height="100" alt="profImage"></img>
                    <div id="srchAccBody">
                        <label id="srchAccUsernmaeLbl" >{username}</label>
                    </div>
                </div>
                <div>
                    <label id="srchAccNameLbl">{name}</label>
                </div>
                <button id="srchAccBtn" onClick={e => clicked(e, parent)} value={username}>View Profile</button>
            </div>
            
        </div>
		);        
}

function clicked(e,page) {
    page.props.history.push("/profile/" + e.target.value);
}