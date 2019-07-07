import React, { Component } from 'react';
import '../css/takenQuiz.css'

const takenQuizes = [
  { quizName: "Do I know JavaScript?" },
  { quizName: "Do I know what I do" }
];

class TakenQuiz extends Component {
    constructor() {
        super();
        this.state = {
          takenQuizes: takenQuizes,
        }
    }

    componentDidMount() {
      var data = null;
      fetch('http://localhost:8080/ServletTakenQuizzes?id=' + 1)
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
