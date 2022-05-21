import React from 'react';
import { useState } from "react";

import EnglishIcon from "./Icons/EnglishIcon";
import ArtIcon from "./Icons/ArtIcon";
import BiologyIcon from "./Icons/BiologyIcon";
import ChemistryIcon from "./Icons/ChemistryIcon";
import GeographyIcon from "./Icons/GeographyIcon";
import HistoryIcon from "./Icons/HistoryIcon";
import ITIcon from "./Icons/ITIcon";
import LiteratureIcon from "./Icons/LiteratureIcon";
import MusicIcon from "./Icons/MusicIcon";
import MathsIcon from "./Icons/MathsIcon";
import PhysicsIcon from "./Icons/PhysicsIcon";
import VietnameseIcon from "./Icons/VietnameseIcon";

interface SubjectProps {
    name: string;
    children?: React.ReactNode; 
  }

function Subject(props : SubjectProps) {
    return (
        <div className="items-stretch p-7 border rounded-2xl border-gray-300 hover:bg-yellow-100 hover:scale-110 hover:border-0 ease-out duration-300">     
            <div className="mb-4">{props.children}</div>
            <p className="text-xl text-center font-medium">{props.name}</p>
        </div>        
    );
}

export default function Subjects() {
    return (
        <div className="py-6 mx-40">
            <h1 className="mb-6 text-2xl font-bold">All Subjects</h1>
            <div className="mb-6 flex justify-between place-items-center">
                <Subject name="Maths"><MathsIcon/></Subject>
                <Subject name="Physics"><PhysicsIcon/></Subject>
                <Subject name="Chemistry"><ChemistryIcon/></Subject>
                <Subject name="Literature"><LiteratureIcon/></Subject>
                <Subject name="English"><EnglishIcon/></Subject>
                <Subject name="Biology"><BiologyIcon/></Subject>
            </div>
            <div className=" flex justify-between place-items-center">
                <Subject name="History"><HistoryIcon/></Subject>
                <Subject name="Geography"><GeographyIcon/></Subject>
                <Subject name="IT"><ITIcon/></Subject>
                <Subject name="Music"><MusicIcon/></Subject>
                <Subject name="Fine Arts"><ArtIcon/></Subject>
                <Subject name="Vietnamese"><VietnameseIcon/></Subject>
            </div>
        </div>
    );
}