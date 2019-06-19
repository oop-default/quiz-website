import React,{Component} from 'react';
import '../css/Left.css';


const popQuizes = [
    {id: 1, quizName: "pirveli", creator: "me"},
    {id: 2, quizName: "meore", creator: "me"},
    {id: 3, quizName: "mesame", creator: "me"}
  ];
  
  const recQuizes = [
    {id: 1, quizName: "pirveli", creator: "meeee"},
    {id: 2, quizName: "meore", creator: "me"},
    {id: 3, quizName: "mesame", creator: "me"}
  ];


class Left extends Component {

    constructor() {
        super();
        this.state = {
          popQuizes: popQuizes,
          recQuizes: recQuizes,
          table: "pop"
          
        }
    }

    render() {
        return (
            <div className = "left">
                <div className = "profile">
                    <a href = "https://www.facebook.com" style = {{textDecoration:"none"}}>
                        <table className="profTable">
                        <tr>
                            <img className="picture" src="male.jpg"></img>
                            <p className = "nickName"><font size="4">vigaca</font></p>
                        </tr></table>
                    </a>
                </div>
                <div className="quizes">
                    <table className="quizesTable">
                        <thead>
                            <tr>
                                <th className="head" colSpan="3">
                                    <i onClick = {()=>this.changeTable()} className="leftArrow"></i>
                                    {this.state.table === "pop" ? "Popular quizes" : " Recent quizes "}
                                    <i onClick = {()=>this.changeTable()}  className="rightArrow"></i>
                                </th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>QuizName</th>
                                <th>Creator</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                            this.state.table === "pop" ? 
                                (this.state.popQuizes.map((quiz) => {
                                    return <tr>
                                        <td><a href="https://www.facebook.com">{quiz.id}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.quizName}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.creator}</a></td>
                                    </tr>
                                }))
                            :
                                (this.state.recQuizes.map((quiz) => {
                                    return <tr>
                                        <td><a href="https://www.facebook.com">{quiz.id}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.quizName}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.creator}</a></td>
                                    </tr>
                                }))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }

    changeTable() {
        if(this.state.table === "pop") {
          this.setState({
            table:"rec"
          });
        } else {
          this.setState({
            table:"pop"
          });
        }
      }
}



export default Left;