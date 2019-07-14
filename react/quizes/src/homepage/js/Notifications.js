import React,{Component} from 'react';
import '../css/Notifications.css';
import AcceptOrDenie from './AcceptOrDenie';

class Notifications extends Component{

    constructor() {
        super();
        this.state={
            requests:null,
            messages:null,
            challenges:null
        }
    }

    //////////////////////////////////////////////////////////////////////////////

    fetchNotes() {
      fetch('http://localhost:8080/ServletNote')
        .then(response=>response.json())
          .then((data) => {
              this.setState({messages: data});
          });
    }

    fetchChallenges() {
      fetch('http://localhost:8080/ServletChallenge')
      .then(response=>response.json())
          .then((data) => {
              this.setState({challenges: data});
          });
    }

    fetchRequests() {
      fetch('http://localhost:8080/ServletFriendRequest')
        .then(response=>response.json())
            .then((data) => {
              this.setState({requests: data});
            });
    }

    componentDidMount() {
      this.fetchChallenges();
      this.fetchRequests();
      this.fetchNotes();
    }

    reply(i) {
        var messageToSend = document.getElementsByClassName("sendingMessage")[i].value;
        if (messageToSend.length > 0) {
          var userToSend = this.state.messages[i].fromId;
          var note = {
            "text":messageToSend,
            "receiverId":userToSend
          };
          this.sendMessage(note,i);
        }
    }

    sendMessage(note, i) {
      fetch('http://localhost:8080/ServletNote', {
          method: 'POST', // or 'PUT'
          body: JSON.stringify(note), // data can be `string` or {object}!
          headers: {
              'Content-Type': 'application/json'
          }
      }).then(response => {
            if (response.status !== 200 ) {
                document.getElementsByClassName("errorMessagePlace")[i].innerHTML = "Some problem occurred, try again";
            } else {
              document.getElementsByClassName("errorMessagePlace")[i].innerHTML = "";
            }
          }).catch(error => {
                  console.log(error);
              });
    }

    answerChallenge(quizId, fromId, answer) {
      var challenge = {
        "quizId":quizId,
        "fromId":fromId,
        "answer":answer
      }
      fetch('http://localhost:8080/ServletChallenge', {
          method: "PUT", // or 'PUT'
          body: JSON.stringify(challenge), // data can be `string` or {object}!
          headers: {
              'Content-Type': 'application/json'
          }
      }).then(response => {
            if (response.status !== 200 ) {
                console.log("erorinio");
            }
          }).catch(error => {
                  console.log(error);
          });
    }

    answerFriendRequest(fromId, answer) {
        var request = {
          "senderId":fromId,
          "status":answer
        }
        fetch('http://localhost:8080/ServletFriendRequest', {
            method: "PUT", // or 'PUT'
            body: JSON.stringify(request), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
              if (response.status !== 200 ) {
                  console.log("erorinio");
              }
            }).catch(error => {
                    console.log(error);
            });
    }
    //////////////////////////////////////////////////////////////////////////////

    render() {
        if (this.state.messages === null || this.state.requests === null || this.state.challenges === null) {
          return (<div clasNames="myLoader"></div>);
        } else {
          console.log(this.state.messages);
          return (
          <div className="nots" >
              <table className = "notsTable">
                  <thead style={{width:"330px"}}>
                      <tr>
                      <th className="heads"><button onClick={()=>this.show("message")} className="notification" id="notHide">
                              <span id="notHide">Messages</span>
                              <span className="badge" id="notHide">{this.state.messages.length}</span>
                          </button>
                      </th>
                      <th className="heads"><button onClick={()=>this.show("challenge")} className="notification" id="notHide">
                              <span id="notHide">Challenges</span>
                              <span className="badge" id="notHide">{this.state.challenges.length}</span>
                          </button>
                      </th>
                      <th className="heads"><button onClick={()=>this.show("request")} className="notification" id="notHide">
                              <span id="notHide">Requests</span>
                              <span className="badge" id="notHide">{this.state.requests.length}</span>
                          </button>
                      </th>
                      </tr>
                  </thead>
                  <tbody className="notsBody">
                      {
                          this.state.messages.map((notification, i) => {
                              return <tr key={notification.fromName}>
                                  <td id = "notHide" colSpan="3" style={{wordBreak:"break-word"}}>
                                      <a href={"http://localhost:3000/profile/:userId"}><h6 className="from">{notification.fromName}:</h6></a>
                                      <div id = "notHide" style={{overflow:"auto"}}>{notification.note}</div>
                                      <input className="sendingMessage" id = "notHide" style={{width:"250px"}}></input>
                                      <button id = "notHide" onClick={()=>this.reply(i)} style={{float:"right"}}>reply</button>
                                      <div className="errorMessagePlace"></div>
                                  </td>
                              </tr>
                          })
                      }
                  </tbody>
                  <tbody className="notsBody">
                      {
                          this.state.challenges.map((notification, i) => {
                              return <tr key={notification.fromName}>
                                  <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                      <a href={"http://localhost:3000/profile/:userId"}>
                                          {notification.fromName}
                                      </a> challenged you to take quiz number {notification.quizId}
                                      <div id="notHide" display="flex">
                                          <button id="notHide" className="accept" onClick={()=>this.answerChallenge(notification.quizId, notification.fromId, "accept")}>
                                              <span id="notHide" style = {{color:"white"}}>&#10003;</span>
                                          </button>
                                          <button id="notHide" className="reject"  onClick={()=>this.answerChallenge(notification.quizId, notification.fromId, "deny")}>
                                              <span id="notHide" style = {{color:"white"}}>&#10006;</span>
                                          </button>
                                      </div>
                                  </td>
                              </tr>
                          })
                      }
                  </tbody>
                  <tbody className="notsBody">
                      {
                          this.state.requests.map((notification) => {
                              return <tr key={notification.fromName}>
                                  <td id="notHide" colSpan="3" style={{textAlign:"center"}}>
                                      <a href={"http://localhost:3000/profile/:userId"}>{notification.fromName}</a> sent you a friend request
                                      <div id="notHide" display="flex">
                                          <button id="notHide" className="accept" onClick={()=>this.answerFriendRequest(notification.fromId, "accept")}>
                                              <span id="notHide" style = {{color:"white"}}>&#10003;</span>
                                          </button>
                                          <button id="notHide" className="reject" onClick={()=>this.answerFriendRequest(notification.fromId, "deny")}>
                                              <span id="notHide" style = {{color:"white"}}>&#10006;</span>
                                          </button>
                                      </div>
                                  </td>
                              </tr>
                          })
                      }
                  </tbody>
              </table>
          </div>);
        }
    }

    show(notification) {
        var nOfElement;
        var ele;
        if(notification ==="message") {
            this.fetchNotes();
            nOfElement = 0
            ele = document.getElementsByClassName("notsBody")[0];
        } else if(notification === "challenge"){
            this.fetchChallenges();
            nOfElement = 1;
            ele = document.getElementsByClassName("notsBody")[1];
        } else {
            this.fetchRequests();
            nOfElement = 2;
            ele = document.getElementsByClassName("notsBody")[2];
        }

        for (let index = 0; index < 3; index++) {
            if(index !== nOfElement) {
                document.getElementsByClassName("notsBody")[index].style.display = "none";
                document.getElementsByClassName("heads")[index].style = "background-color: forestgreen";
            }
        }
        if(getComputedStyle(ele, null).display === "none") {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: #1dc407; border-radius: 5px";
            ele.style.display = "block";
        } else {
            document.getElementsByClassName("heads")[nOfElement].style = "background-color: forestgreen";
            ele.style.display = "none";
        }
    }

}



export default Notifications;
