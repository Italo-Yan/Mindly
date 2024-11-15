import { Box } from "../../components/box/Box";
import { ScheduleProfessional } from "../../components/schedule/ScheduleProfessional";
import { Description } from "../../components/description/Description";

export function ScheduleProfessionalPage() {
  return (
    <>
      <Description />
      <Box name="Agendamento" />
      <ScheduleProfessional />
    </>
  );
}


