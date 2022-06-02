import React from "react";
import axios from "axios";
import StudentRow from "./StudentRow";

class StudentTable extends React.Component {
  state = {
    students: []
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/students")
      .then((res) => {
        const students = res.data;
        this.setState({ students });
      })
      .catch((error) => console.log(error));
  }

  render() {
    return (
      <table className="table table-striped">
        <thead className="sticky-top">
          <tr className="bg-success">
            <th className="text-light">Student ID</th>
            <th className="text-light">Fullname</th>
            <th className="text-light">Date of birth</th>
            <th className="text-light">Address</th>
            <th className="text-light"></th>
            <th className="text-light"></th>
          </tr>
        </thead>
        <tbody>
          {/*{this.state.students.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

          {Object.keys(this.state.students).map((key) => (
            <StudentRow key={key} student={this.state.students[key]} />
          ))}
        </tbody>
      </table>
    );
  }
}

export default StudentTable;
