import React from "react";
import ClassRow from "./ClassRow";
import axios from "axios";

class ClassTable extends React.Component {
  state = {
    classes: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/classes")
      .then((res) => {
        const classes = res.data;
        this.setState({ classes });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Class ID</th>
            <th className="text-light">Tutor ID</th>
            <th className="text-light">Student ID</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.Classs.map((Class) => (
            <ClassRow Class={Class} />
          ))} */}

          {Object.keys(this.state.classes).map((key) => (
            <ClassRow key={key} class1={this.state.classes[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default ClassTable;
