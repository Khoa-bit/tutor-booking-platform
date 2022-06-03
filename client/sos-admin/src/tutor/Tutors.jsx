import React from "react";
import { Link } from "react-router-dom";
import TutorTable from "./TutorTable";

class Tutors extends React.Component {
  render() {
    return (
      <div className="container">
        <header className="mt-5">
          <h1 className="text-center">Tutors</h1>
        </header>
        <main className="mt-5">
          <div>
            <TutorTable />
          </div>
        </main>
        <footer className=" mt-5 mb-5">
          <div className="d-flex justify-content-center align-items-center">
            <Link to="/" className="btn btn-warning mx-3">
              Create new Tutor
            </Link>
            <Link to="/" className="btn btn-success mx-3">
              Home
            </Link>
          </div>
        </footer>
      </div>
    );
  }
}

export default Tutors;
