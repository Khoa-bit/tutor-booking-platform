import { useState } from "react";
import { Listbox, Transition } from "@headlessui/react";
import { CheckIcon, ChevronDownIcon } from '@heroicons/react/solid'

const city = ["Ho Chi Minh", "Ha Noi", "Hai Phong", "Da Nang", "Nghe An", "Thanh Hoa"];

export default function Filter() {
  const [selectedCity, setSelectedCity] = useState("Choose City/Province");

  return (
      <Listbox className="w-52 max-w-xs mr-4" as="div" value={selectedCity} onChange={setSelectedCity}>
        {({ open }) => (
          <>
            <div className="relative">
              <span className="inline-block w-52">
                <Listbox.Button className="pl-3 py-2 w-52 text-left focus:outline-none focus:shadow-outline-blue focus:border-primary-default relative border shadow-sm border-grey-light rounded text-black bg-white">
                  <span className="block truncate">{selectedCity}</span>
                  <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                    <ChevronDownIcon
                      className="h-5 w-5 text-grey-default"
                      aria-hidden="true"
                    />
                  </span>
                </Listbox.Button>
              </span>
              <Transition
                show={open}
                leave="transition ease-in duration-100"
                leaveFrom="opacity-100"
                leaveTo="opacity-0"
              >
                <Listbox.Options
                  static
                  className="border border-grey-light rounded mt-1 bg-white "
                >
                  {city.map((city) => (
                    <Listbox.Option key={city} value={city}>
                      {({ selected, active }) => (
                        <div
                          className={`${
                            active
                              ? "text-black bg-grey-lighter"
                              : "text-black"
                          } cursor-default select-none relative py-2 pl-10 pr-4`}
                        >
                          <span
                            className={`${
                              selected ? "font-semibold" : "font-normal"
                            }`}
                          >
                            {city}
                          </span>

                          {selected && (
                            <span
                              className={`${
                                active ? "text-primary-default" : "text-primary-default"
                              } absolute inset-y-0 left-0 flex items-center pl-2`}
                            >
                              <svg
                                className="h-5 w-5"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 20 20"
                                fill="currentColor"
                              >
                                <path
                                  fillRule="evenodd"
                                  d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
                                  clipRule="evenodd"
                                />
                              </svg>
                            </span>
                          )}
                        </div>
                      )}
                    </Listbox.Option>
                  ))}
                </Listbox.Options>
              </Transition>
            </div>
          </>
        )}
      </Listbox>
  );
}