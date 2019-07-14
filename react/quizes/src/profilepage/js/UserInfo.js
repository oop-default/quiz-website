import React, { Component } from 'react';
import '../css/userInfo.css'

const status = [
  "me", "myFriend", "iRequested", "requestedMe", "other"
];

const info = {
  first_name: "",
  last_name: "",
  username: "",
  image: "",
  status: "",
  is_admin: null
};

class UserInfo extends Component {
    constructor(params) {
        super(params);
        console.log("username: " + params.username);
        this.state = {
          info: info,
          username: params.username,
          showPopUp: false
        }
    }

    componentDidMount() {
        console.log("componentDidMount");
        this.fetchUserInfo();
    }

    componentDidUpdate(prevProps, prevState) {
        console.log("componentDidUpdate");
    }

//// RENDER ADMIN ////////////////////////////////////////////////////////////////////////
    renderAdmin() {
      if (this.state.info.is_admin)
        return <p className="adminStatus"><i class="fas fa-crown">ADMIN</i></p>;
      else
        return <p className="adminStatus"></p>;
    }


//// FETCH USER INFO ////////////////////////////////////////////////////////////////////////
    fetchUserInfo() {
      console.log("catch yo");
      var url = "http://localhost:8080/ServletAccountInfo?username=" + this.state.username;
      fetch(url).then(response => response.json()).then((data) => {
        console.log(data);
          this.setState({info: data})
      }).catch(error => {
            console.log(error);
      });
    }

//// DELETE FRIEND ////////////////////////////////////////////////////////////////////////
    postDeleteFriend() {
      console.log(this.state.info.status);
      var url = 'http://localhost:8080/ServletFriends?username=' + this.state.username;
      fetch(url, {
          method: 'POST',
          headers: {'Content-Type':'application/json'},
          body: {  }
        }).then(response => {
            console.log(this.state.info.status);
            if (response.status !== 200 ) {
                console.log("some error occured");
            }
          }).catch(error => {
                console.log(error);
          });
    }

    deleteFriend() { // +++ doDeleteFriend,setStatus -> other, show plusButton

      this.postDeleteFriend();
      this.fetchUserInfo();
      console.log(this.state.info.status);
      //this.forceUpdate();
      // this.componentDidUpdate();
      // this.renderSwitch(this.state.info.status);
    }

//// ADD FRIEND ////////////////////////////////////////////////////////////////////////
    addFriend() {
      this.setState(info => ({
        status: "myFriend"
      }));
    }

//// SEND FRIEND REQUEST ////////////////////////////////////////////////////////////////////////
    postDataSendRequest() {
      var url = 'http://localhost:8080/ServletFriendRequests?username=' + this.state.info.username;
      fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
      }).then(response => {
            if (response.status !== 200 ) {
                console.log("some error occured");
            }
          }).catch(error => {
                  console.log(error);
              });
    }

    sendFriendRequest() {
      this.postDataSendRequest();
      this.fetchUserInfo();
      this.forceUpdate();
      this.setState({ info: this.info });
    }

//// DELETE FRIEND REQUEST ////////////////////////////////////////////////////////////////////////
    deleteRequest() {
      console.log("deleted Request");
      this.setState(info => ({
        status: "other"
      }));
    }

    send() {
      var messageToSend = document.getElementById("noteId").value;
      if (messageToSend.length > 0) {
        var userToSend = this.state.info.id;
        var note = {
          "text":messageToSend,
          "receiverId":userToSend
        };
        this.sendMessage(note);
      }
    }

    sendMessage(note) {
      fetch('http://localhost:8080/ServletNote', {
          method: 'POST', // or 'PUT'
          body: JSON.stringify(note), // data can be `string` or {object}!
          headers: {
              'Content-Type': 'application/json'
          }
      }).then(response => {
            if (response.status !== 200 ) {
                document.getElementById("messageId").innerHTML = "Some problem occurred, try again";
            } else {
              document.getElementById("errorMessagePlace").innerHTML = "";
                  this.showPop();
            }
          }).catch(error => {
                  console.log(error);
              });
    }


///////////////////////////////////////////////////////////////////////////////////////////////////
    renderSwitch(param) {
      switch(param) {
        case "me":
          return null;
        case "myFriend": // REMOVE FRIEND
          return <i id="addFriend" className="fas fa-user-minus" onClick= {() => this.deleteFriend() } ></i>; // check, back to notMyFriend
        case "requestedMe": // ADD FRIEND
          return <i id="addFriend" className="fa fa-user-plus" onClick= {() => this.addFriend() }></i>; // ONCLICK -> just add
        case "iRequested": // DELETE REQUEST
           return <i id="addFriend" className="fa fa-user-times" onClick={() => this.deleteRequest() }></i>; // back to notMyFriend
        default: // not my friend => ADD FRIEND
          return <i id="addFriend" className="fa fa-user-plus" onClick= {() => this.sendFriendRequest() }></i>; // ONCLICK
      }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////

    showPop() {
      var showOrHide = !this.state.showPopUp;
      this.setState({
        showPopUp: showOrHide
      })
    }

    render() {
      if (this.state.info === null) {
        return (<div>LOADING...</div>);
      } else {
        return (
          <div>
          <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous"/>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
          <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous"/>
          <div className="profPic">
            <img id="avatar" src = "https://www.google.com/search?q=cute+cub&client=ubuntu&hs=1u7&channel=fs&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiwyZzA6bLjAhWQVBUIHTCMDmcQ_AUIECgB&biw=1656&bih=799#imgrc=r2Ok7XJ4ud7BKM:"/>
          </div>
          <div>

              {this.renderAdmin()}

            <p className="nameLastname">
              {this.state.info.first_name + " " + this.state.info.last_name}

              <div className="buttons">
                  {this.renderSwitch(this.state.info.status)}
                  <i onClick={()=>this.showPop()} className="far fa-envelope"></i>
                  { this.state.showPopUp ?
                    <div className = "popWindow">
                      <table>
                        <label style={{color:"red", fontSize:"10px", display:"block"}} id="messageId"></label>
                        <tr>
                          <td colSpan="2">
                            <textarea id="noteId" style={{width:"200px", height:"100px", resize:"vertical"}}></textarea>
                          </td>
                        </tr>
                        <tr>
                          <td style={{textAlign:"center"}}>
                            <button onClick={()=>this.send()}>send</button>
                          </td>
                          <td style={{textAlign:"center"}}>
                            <button onClick={()=>this.showPop()}>cancel</button>
                          </td>
                        </tr>
                      </table>
                    </div> : null
                  }
              </div>
              
              <p className="username"> @{this.state.info.username} </p>
            </p>
          </div>
          </div>
        );
      }
    }

  }

  export default UserInfo;
