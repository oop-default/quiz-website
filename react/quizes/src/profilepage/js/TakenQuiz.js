import React, { Component } from 'react';
import '../css/takenQuiz.css'

class TakenQuiz extends Component {
    constructor() {
        super();
        this.state = {
          takenQuizes: [],
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
                var url = "localHost::8080/quizes?id=" + tknqz.id;
                return <tr>
                  <td>
                    <a href={url}>{tknqz.title}</a>
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
