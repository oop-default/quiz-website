import React,{Component} from 'react';
import '../css/Categories.css'

const categories = [
    {id: 1, name: "suyvela"},
    {id: 2, name: "magadi"},
    {id: 3, name: "imnairi"}
];

class Categories extends Component{
    constructor() {
        super();
        this.state = {
          categories: categories,
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/ServletCategories')
          .then((response) => {
              response.json().then((data) => {
                this.setState({categories: data});
              })
            });
    }


    render() {
        return (
            <div>
                <table className="categoriesTable">
                    <thead>
                        <tr>
                            <th>Categories</th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.categories.map((category, i) => {
                            var url = "https://www.facebook.com/" + category.id;
                            return <tr key={i}>
                                <td onClick={() => window.location.href=url}>
                                    {category.name}
                                </td>
                            </tr>
                        })
                    }
                    </tbody>
                </table>
            </div>
        )
    }

}

export default Categories;