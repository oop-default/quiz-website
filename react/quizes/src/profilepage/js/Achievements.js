import React, { Component } from 'react';
import '../css/achievements.css'

const badges = [
  { icon: "fas fa-award" },
  { icon: "fas fa-medal" }
];

class Achievements extends Component {
    constructor() {
        super();
        this.state = {
          badges: badges,
        }
    }

    render() {
      return (
        <div className="achievementsSection">
          <table id="achievementsTable">
            <tr>
              <th id="achievementsLabel">
                <i className="fa fa-trophy" aria-hidden="true"></i>  Achievements
              </th>
            </tr>
            {
              this.state.badges.map((bdg) => {
                return <tr>
                <td><i className= "{bdg.icon} fa-5x"></i></td>
                </tr>
              })
            }
          </table>
        </div>
    );
  }
}

  export default Achievements;
