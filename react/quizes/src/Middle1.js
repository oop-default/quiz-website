import React,{Component} from 'react';
import TakenQuiz from './TakenQuiz'
import '../css/middle1.css'


class Middle1 extends Component {
    constructor() {
      super();
    }

    render() {
        return (
            <div className ="middlepane1">
                <TakenQuiz></TakenQuiz>
            </div>
        );
    }
}

export default Middle1;
