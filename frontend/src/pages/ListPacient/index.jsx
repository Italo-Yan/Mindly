import { Description } from "../../components/description/Description";
import { Box } from "../../components/box/Box";
import { ListPacients } from "../../components/listPacient/ListPacient";

export function ListPacient() {
  return (
    <>
      <Description />
      <Box name="Lista de Pacientes" />
      <ListPacients />
    </>
  )
}