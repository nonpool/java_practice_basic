import entity.Address;
import entity.Email;
import entity.MasterNumber;
import entity.Person;
import entity.Telephone;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class PersonSet {

  private List<MasterNumber> masterNumbers;

  private List<Address> addresses;

  private List<Telephone> telephones;

  private List<Email> emails;

  public PersonSet(List<MasterNumber> masterNumbers,
                   List<Telephone> telephones,
                   List<Address> addresses,
                   List<Email> emails) {
    this.masterNumbers = masterNumbers;
    this.addresses = addresses;
    this.telephones = telephones;
    this.emails = emails;
  }

  public Stream<Person> groupToPeople() {
    // TODO: group the data to Stream<Person>
    Map<String, List<Telephone>> telephoneMap = telephones.stream().collect(groupingBy(Telephone::getMasterNumber));
    Map<String, List<Address>> addressMap = addresses.stream().collect(groupingBy(Address::getMasterNumber));
    Map<String, List<Email>> emailMap = emails.stream().collect(groupingBy(Email::getMasterNumber));
    return masterNumbers.stream().map(masterNumber -> {
      String number = masterNumber.getNumber();
      return new Person(number, telephoneMap.getOrDefault(number, new ArrayList<>()), buildAddress(addressMap, number), emailMap.getOrDefault(number, new ArrayList<>()));
    });
  }

  private Address buildAddress(Map<String, List<Address>> addressMap, String number) {
    List<Address> list = addressMap.getOrDefault(number, new ArrayList<>());
    if (list.size() == 0) {
      return null;
    }
    return list.get(0);
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public List<Telephone> getTelephones() {
    return telephones;
  }

  public List<Email> getEmails() {
    return emails;
  }

  public void setMasterNumbers(List<MasterNumber> masterNumbers) {
    this.masterNumbers = masterNumbers;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public void setEmails(List<Email> emails) {
    this.emails = emails;
  }

  public void setTelephones(List<Telephone> telephones) {
    this.telephones = telephones;
  }
}
