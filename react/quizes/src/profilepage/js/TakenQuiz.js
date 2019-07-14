import React, { Component } from 'react';
import '../css/takenQuiz.css';
import cookie from 'react-cookies';

const takenQuizes = [
  {id: 1, title: "Do I know JavaScript?" },
  {id: 2, title: "Do I know what I do" }
];

class TakenQuiz extends Component {
    constructor() {
        super();
        this.state = {
          takenQuizes: takenQuizes,
        }
    }

    componentDidMount() {
      var token = cookie.load("jwt");
      var bearer = "Bearer " + token;
      fetch('http://localhost:8080/ServletTakenQuizzes', {
        headers: {
          'Authorization': bearer
        }
      })
        .then((response) => {
            response.json().then(data => {
              this.setState({takenQuizes: data});
            })
          });
    }

    render() {
      return (
        <div id="takenQuizSection">
          <table id="takenQuiz">
            <tr>
              <th id="takenQuizLabel">Taken Quizes :</th>
            </tr>
            {
              this.state.takenQuizes.map((tknqz) => {
                return <tr>
                <td>
                {tknqz.title}
                </td>
                </tr>
              })
            }
          </table>
        </div>
      );
    }
  }

  export default TakenQuiz;
