import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import '../css/friendsList.css'

const friends = [
  { username: "vika_sh" },
  { username: "zvikikiki" },
  { username: "jiksi" },
  { username: "vaxushti" },
  { username: "alshiba" }
];

class FriendsList extends Component {
    constructor() {
        super();
        this.state = {
          friends: friends,
        }
    }

    render() {
      return (
        <div id="friendsSection">
          <table id="friendsTable">
            <tr>
              <th id="friendsLabel">
                <i className="fa fa-users" aria-hidden="true"></i>  Friends
              </th>
            </tr>
              {
                this.state.friends.map((unm) => {
                  return <tr>
                    <td>
                    {unm.username}
                    </td>
                  </tr>
                  })
              }
          </table>
        </div>
    );
  }
  }

  export default FriendsList;
