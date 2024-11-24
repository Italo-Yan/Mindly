import { Box } from "../../components/box/Box";
import { SchedulePatient } from "../../components/schedule/schedulePatient";
import { Description } from "../../components/description/Description";

export function SchedulePatientPage() {
  return (
    <>
      <Description />
      <Box name="Agendamento" />
      <SchedulePatient />
    </>
  );
}
