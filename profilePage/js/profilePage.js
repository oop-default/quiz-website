import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import '../css/profilePage.css'

class profilePage extends Component {
    constructor() {
        super();
        console.log("profilePage");
    }
    render() {
        return (
          <div className="container">
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"/>
            <div className="leftpane">
              <div className="profPic">
                <img id="avatar"src="https://gix.uw.edu/wp-content/uploads/2019/01/photo-placeholder.jpeg"/>
              </div>
              <div>
                <p className="nameLastname">
                  John Doe
                  <i id="addFriend" className="fas fa-user-plus" onclick="addFriend()"></i>
                  <p className="username">@johnny_doe</p>
                </p>
              </div>
            <div id="friendsSection">
              <table id="friendsTable">
                <tr>
                  <th id="friendsLabel">
                    <i className="fa fa-users" aria-hidden="true"></i>  Friends
                  </th>
                </tr>
              </table>
            </div>
            </div>
          <div className="middlepane1">
            <div id="takenQuizSection">
              <table id="takenQuiz">
                <tr>
                  <th id="takenQuizLabel">Taken Quizes :</th>
                </tr>
              </table>
            </div>
          </div>
          <div className="middlepane2">
            <div id="createdQuizSection">
              <table id="createdQuiz">
                <tr>
                  <th id="createdQuizLabel">Created Quizes :</th>
                </tr>
              </table>
            </div>
          </div>
            <div className="rightpane">
              <div className="achievementsSection">
                <table id="achievementsTable">
                  <tr>
                    <th id="achievementsLabel">
                      <i className="fa fa-trophy" aria-hidden="true"></i>  Achievements
                    </th>
                  </tr>
                  <tr>
                    <td>
                      <i className="fas fa-award fa-5x"></i>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <i className="fas fa-medal fa-5x"></i>
                    </td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        );
    }
}
export default profilePage;
