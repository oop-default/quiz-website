import React,{Component} from 'react';
import CreatedQuiz from './CreatedQuiz'
import '../css/middle2.css'


class Middle2 extends Component {
    constructor() {
      super();
    }

    render() {
        return (
            <div className ="middlepane2">
                <CreatedQuiz></CreatedQuiz>
            </div>
        );
    }
}

export default Middle2;
