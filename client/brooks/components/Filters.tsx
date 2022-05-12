import { useState } from "react";
import { Listbox, Transition } from "@headlessui/react";
import { CheckIcon, ChevronDownIcon } from '@heroicons/react/solid'

import Filter from "./Filter"

export default function Filters() {
    return (
        <div className="">
            <div className="flex h-10">
                <Filter />
                <Filter />
                <Filter />
                <button type="button"
                        className="h-11 inline-flex items-center justify-center rounded-md border border-transparent bg-primary-default px-4 py-2 text-sm font-medium text-white hover:bg-primary-dark focus:outline-none focus-visible:ring-primary-default "
                        >Apply</button>
            </div>
            
        </div>
    );
}
