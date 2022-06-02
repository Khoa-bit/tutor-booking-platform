import React from "react";
import TutorRow from "./TutorRow";
import axios from "axios";

class TutorTable extends React.Component {
  state = {
    tutors: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/tutors")
      .then((res) => {
        const tutors = res.data;
        this.setState({ tutors });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Tutor ID</th>
            <th className="text-light">Fullname</th>
            <th className="text-light">Date of birth</th>
            <th className="text-light">Address</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.tutors.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

          {Object.keys(this.state.tutors).map((key) => (
            <TutorRow key={key} tutor={this.state.tutors[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default TutorTable;
