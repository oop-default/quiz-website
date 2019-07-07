import React, { Component } from 'react';
import '../css/userInfo.css'

const info = {
  firstname: "Vika",
  lastname: "Shonia",
  username: "vika_sh",
  image: "https://gix.uw.edu/wp-content/uploads/2019/01/photo-placeholder.jpeg"
};

class UserInfo extends Component {
    constructor() {
        super();
        this.state = {
          info: info,
        }
    }

    render() {
      return (
        <div>
        <div className="profPic">
        {
          <img id="avatar" src={"\"" + this.state.info.image + "\""}/>
        }
        </div>
        <div>
          <p className="nameLastname">
            {this.state.info.firstname + " " + this.state.info.lastname}
            <i id="addFriend" className="fas fa-user-plus" onclick="addFriend()"></i>
              <p className="username"> @{this.state.info.username} </p>
          </p>
        </div>
        </div>
      );
    }

  }

  export default UserInfo;
