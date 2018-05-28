package main_class;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Window extends JFrame
{
    private int znalezione=0;
    private int maxLiczbaFilmow=20;
    private int index=0;
    private String zmiennaGlobal="";

    private int ileLini(String nazwa)
    {
        int linie=0;
        String tekst="";
        try
        {
            FileReader file = new FileReader(nazwa);
            BufferedReader reader = new BufferedReader(file);

            while((tekst = reader.readLine()) != null)
            {
                linie++;
            }
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        return linie;
    }

    private String[] odczytFilmow(String nazwa)
    {
        int ile = ileLini("filmy.txt");
        String tekst;
        String spisFilmow="";
        String[] tablica = new String[ile];

        try
        {
            FileReader file = new FileReader(nazwa);
            BufferedReader reader = new BufferedReader(file);

            for(int i=0;i<tablica.length;i++)
            {
                if((tekst = reader.readLine()) != null)
                {
                    spisFilmow = tekst;
                    tablica[i] = spisFilmow;
                }

            }
            file.close();
            reader.close();
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"Nie odnaleziono pliku","Błąd",JOptionPane.INFORMATION_MESSAGE);
        }

        return tablica;
    }

    public String[] wyszukiwanie(String slowo, String nazwaPliku)
    {
        int ile = ileLini(nazwaPliku);
        String[] wynik = new String[ile];

        try
        {
            String[] zmienna;

            zmienna = odczytFilmow(nazwaPliku);

            int intIndeks;


            for(int i = 0;i<zmienna.length;i++)
            {
                intIndeks = zmienna[i].indexOf(slowo);

                if(intIndeks != -1)
                {
                    wynik[i] = zmienna[i];
                    znalezione++;
                }
            }
        }
        catch(NullPointerException ex)
        {

        }
        String[] result = new String[znalezione];
        for(int i = 0,j=0;i<wynik.length;i++)
        {
            if(wynik[i] != null)
            {
                result[j] = wynik[i];
                j++;
            }
        }
        return result;
    }

    public void ukryj()
    {
        usunFilm.setVisible(false);
        dodajFilm.setVisible(false);
        rezerwuj.setVisible(false);
        zwrot.setVisible(false);
        Rezerwacje.setVisible(false);
        Wypozyczenia.setVisible(false);
        Filmy.setVisible(false);
        powrot.setVisible(true);
        anuluj.setVisible(false);
    }


    private static int szerokosc = 800;
    private static int wysokosc = 620;
    private JPanel panel;
    private JLabel tytul;
    private JLabel usunEtykieta;
    private JLabel szukajEtykieta;
    private JLabel imie;
    private JLabel nazwisko;
    private JLabel nrDowodu;
    private JLabel zwrotEtykieta;
    private JLabel anulujEtykieta;


    private JTextField nazwaFilmu;
    private JTextField wpiszImie;
    private JTextField wpiszNazwisko;
    private JTextField wpiszNrDowodu;
    private JTextField wpiszTytul;

    private JButton dodajFilm;
    private JButton usunFilm;
    private JButton dodajAction;
    private JButton usunAction;
    private JButton rezerwuj;
    private JButton zwrot;
    private JButton Rezerwacje;
    private JButton Wypozyczenia;
    private JButton Filmy;
    private JButton rezerwujAction;
    private JButton powrot;
    private JButton dalej;
    private JButton dodanie;
    private JButton wypozycz;
    private JButton zwrotAction;
    private JButton anuluj;
    private JButton anulujAction;
    private JButton rezerwacjaWypozycz;
    private JButton wypozyczFilm;


    private JList lista;
    private JList listaFilmy;
    private JList listaWypozyczenia;
    private JList listaRezerwacje;
    private JList listaUsuwanie;
    private JList listaZwrot;
    private JList listaAnuluj;


    public Window()
    {

        super("Wypożyczalnia filmów");
        setSize(szerokosc,wysokosc);
        setLocationByPlatform(true);

        panel = new JPanel();

        powrot = new JButton("Powrót do głównego MENU");
        powrot.setBounds(290, 500, 200, 60);
        add(powrot);
        powrot.setVisible(false);

        DefaultListModel modelLista = new DefaultListModel();
        listaFilmy = new JList(modelLista);
        listaFilmy.setBounds(210,60,370,400);
        add(listaFilmy);
        listaFilmy.setVisible(false);

        DefaultListModel modelWypozyczen = new DefaultListModel();
        listaWypozyczenia = new JList(modelWypozyczen);
        listaWypozyczenia.setBounds(210,80,370,370);
        add(listaWypozyczenia);
        listaWypozyczenia.setVisible(false);

        DefaultListModel modelRezerwacji = new DefaultListModel();
        listaRezerwacje = new JList(modelRezerwacji);
        listaRezerwacje.setBounds(210,80,370,370);
        add(listaRezerwacje);
        listaRezerwacje.setVisible(false);

        DefaultListModel model = new DefaultListModel();
        lista = new JList(model);
        lista.setBounds(240,120,310,250);
        add(lista);
        lista.setVisible(false);

        DefaultListModel modelUsuwanie = new DefaultListModel();
        listaUsuwanie = new JList(modelUsuwanie);
        listaUsuwanie.setBounds(210,60,370,400);
        add(listaUsuwanie);
        listaUsuwanie.setVisible(false);

        DefaultListModel modelZwrot = new DefaultListModel();
        listaZwrot = new JList(modelZwrot);
        listaZwrot.setBounds(210,60,370,400);
        add(listaZwrot);
        listaZwrot.setVisible(false);

        DefaultListModel modelAnuluj = new DefaultListModel();
        listaAnuluj = new JList(modelAnuluj);
        listaAnuluj.setBounds(210,60,370,400);
        add(listaAnuluj);
        listaAnuluj.setVisible(false);

        powrot.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                usunFilm.setVisible(true);
                dodajFilm.setVisible(true);
                rezerwuj.setVisible(true);
                zwrot.setVisible(true);
                Rezerwacje.setVisible(true);
                Wypozyczenia.setVisible(true);
                Filmy.setVisible(true);
                powrot.setVisible(false);
                zwrotAction.setVisible(false);
                zwrotEtykieta.setVisible(false);


                tytul.setVisible(false);
                dodajAction.setVisible(false);
                usunEtykieta.setVisible(false);
                usunAction.setVisible(false);
                nazwaFilmu.setVisible(false);
                szukajEtykieta.setVisible(false);
                rezerwujAction.setVisible(false);
                listaFilmy.setVisible(false);
                lista.setVisible(false);
                dalej.setVisible(false);
                imie.setVisible(false);
                nazwisko.setVisible(false);
                nrDowodu.setVisible(false);
                wpiszImie.setVisible(false);
                wpiszNazwisko.setVisible(false);
                wpiszNrDowodu.setVisible(false);
                dodanie.setVisible(false);
                wypozycz.setVisible(false);
                modelLista.removeAllElements();
                modelWypozyczen.removeAllElements();
                modelRezerwacji.removeAllElements();
                model.removeAllElements();
                modelUsuwanie.removeAllElements();
                modelZwrot.removeAllElements();
                modelAnuluj.removeAllElements();
                listaWypozyczenia.setVisible(false);
                listaRezerwacje.setVisible(false);
                wpiszTytul.setVisible(false);
                listaUsuwanie.setVisible(false);
                listaZwrot.setVisible(false);
                anuluj.setVisible(true);
                anulujAction.setVisible(false);
                anulujEtykieta.setVisible(false);
                listaAnuluj.setVisible(false);
                rezerwacjaWypozycz.setVisible(false);
                wypozyczFilm.setVisible(false);
            }
        });

        tytul = new JLabel("Tytuł filmu: ");
        tytul.setBounds(180,30,100,30);//etykiety
        add(tytul);
        tytul.setVisible(false);

        usunEtykieta = new JLabel("Wybierz film do usunięcia:");
        usunEtykieta.setBounds(315,30,150,30);
        add(usunEtykieta);
        usunEtykieta.setVisible(false);

        anulujEtykieta = new JLabel("Wybierz film do anulowania rezerwacji:");
        anulujEtykieta.setBounds(275,30,230,30);
        add(anulujEtykieta);
        anulujEtykieta.setVisible(false);

        dodajFilm = new JButton("Dodaj film");
        dodajFilm.setBounds(620,120,160,60);
        add(dodajFilm);

        wpiszTytul = new JTextField();
        wpiszTytul.setBounds(180,70,300,30);
        add(wpiszTytul);
        wpiszTytul.setVisible(false);

        dodajFilm.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ukryj();

                tytul.setVisible(true);
                dodajAction.setVisible(true);
                wpiszTytul.setVisible(true);
            }
        });


        dodajAction = new JButton("Dodaj!");//dodanie filmu
        dodajAction.setBounds(180,150,130,30);
        add(dodajAction);
        dodajAction.setVisible(false);

        dodajAction.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                boolean takNie=false;

                String zmienna = wpiszTytul.getText();
                int ile = ileLini("filmy.txt");

                if(ile > maxLiczbaFilmow)
                {
                    JOptionPane.showMessageDialog(null,"Zbyt wiele filmów w bazie!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(zmienna.length() < 2)
                {
                    JOptionPane.showMessageDialog(null,"Tytuł musi mieć conajmniej 2 litery!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(zmienna.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Musisz wpisać tytuł filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    String[] tmp = odczytFilmow("filmy.txt");
                    for(int i =0;i<tmp.length;i++)
                    {
                        if(tmp[i].equals(zmienna))
                        {
                            takNie = true;
                        }
                    }
                    if(takNie)
                    {
                        JOptionPane.showMessageDialog(null,"Podany film już jest w bazie!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        try
                        {
                            FileWriter file = new FileWriter("filmy.txt", true);
                            file.append(zmienna+"\r\n");

                            file.close();
                        }
                        catch(IOException ex)
                        {}

                        JOptionPane.showMessageDialog(null,"Dodano film!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                wpiszTytul.setText("");
            }
        });

        usunAction = new JButton("Usuń!");//usuniecie filmu
        usunAction.setBounds(340,465,100,30);
        add(usunAction);
        usunAction.setVisible(false);

        usunFilm = new JButton("Usuń film");
        usunFilm.setBounds(620,210,160,60);
        add(usunFilm);

        usunFilm.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int ile = ileLini("filmy.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak filmów!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    ukryj();
                    usunEtykieta.setVisible(true);
                    usunAction.setVisible(true);
                    String[] wynik;
                    wynik = odczytFilmow("filmy.txt");

                    Arrays.sort(wynik);
                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelUsuwanie = (DefaultListModel)listaUsuwanie.getModel();
                        modelUsuwanie.add(j,wynik[j]);
                    }
                    listaUsuwanie.setVisible(true);
                }

            }
        });

        usunAction.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {

                int ile = ileLini("filmy.txt");
                int i = listaUsuwanie.getSelectedIndex();

                if(i == -1)
                {
                    JOptionPane.showMessageDialog(null,"Nie wybrałeś filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    zmiennaGlobal = (String)modelUsuwanie.elementAt(i);
                    boolean takNie=false;

                    int liczba = ileLini("usuwanie.txt");

                    String[] usuwanie;
                    usuwanie = odczytFilmow("usuwanie.txt");

                    System.out.println(ile);
                    for(int j = 0;j<liczba;j++)
                    {

                        if (usuwanie[j].equals(zmiennaGlobal))
                        {
                            takNie = true;
                        }
                    }

                    if(takNie)
                    {
                        JOptionPane.showMessageDialog(null,"Nie można usunąć filmu. Jest zarezerwowany lub wypożyczony!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        Object[] opcje = { "Tak", "Nie"};
                        int wybrana = JOptionPane.showOptionDialog(null,"Czy na pewno chcesz usunąć film?", "Usuń",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opcje, opcje[1]);

                        if(wybrana == 0)
                        {
                            modelUsuwanie.remove(i);

                            String[] nowaTablica = new String[ile-1];

                            for(int j=0;j<modelUsuwanie.size();j++)
                            {
                                nowaTablica[j] = (String)modelUsuwanie.elementAt(j);
                            }

                            try
                            {
                                PrintWriter file = new PrintWriter("filmy.txt");
                                FileWriter file1 = new FileWriter("filmy.txt",true);

                                file.print("");

                                for(String a: nowaTablica)
                                {
                                    file1.append(a);
                                    file1.append("\r\n");
                                }

                                file.close();
                                file1.close();
                            }
                            catch(IOException ex)
                            {}

                            JOptionPane.showMessageDialog(null,"Usunięto film!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        nazwaFilmu = new JTextField();
        nazwaFilmu.setBounds(220,70,300,30);
        add(nazwaFilmu);
        nazwaFilmu.setVisible(false);

        rezerwuj = new JButton("Zarezerwuj/Wypożycz");
        rezerwuj.setBounds(15,120,160,60);
        add(rezerwuj);

        szukajEtykieta = new JLabel();
        szukajEtykieta.setBounds(220,30,150,30);
        add(szukajEtykieta);

        rezerwuj.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ukryj();

                szukajEtykieta.setText("Wpisz tytuł filmu: ");
                szukajEtykieta.setVisible(true);
                nazwaFilmu.setVisible(true);
                rezerwujAction.setVisible(true);
            }
        });

        rezerwujAction = new JButton("Szukaj");
        rezerwujAction.setBounds(220,120,100,30);
        add(rezerwujAction);
        rezerwujAction.setVisible(false);

        rezerwujAction.addActionListener(new Akcja() //Actionlistener - klasa adaptacyjna
        {
            @Override
            public void actionPerformed(ActionEvent ae) //klasa wewnetrzna
            {

                String nazwa = nazwaFilmu.getText();


                odczytFilmow("filmy.txt");

                String[] wynik;
                wynik = wyszukiwanie(nazwa,"filmy.txt");

                String[] pom = new String[wynik.length];

                /*for(int i=0;i<wynik.length;i++)
                {
                    pom[i] = wynik[i].toLowerCase();
                    System.out.println(pom[i]);
                }*/

                if(nazwaFilmu.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Wpisz nazwe!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(nazwaFilmu.getText().length() < 2)
                {
                    JOptionPane.showMessageDialog(null,"Wpisz conajmniej 2 litery","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(znalezione == 0)
                {
                    JOptionPane.showMessageDialog(null,"Nie znaleziono filmu","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    szukajEtykieta.setVisible(false);
                    nazwaFilmu.setVisible(false);
                    rezerwujAction.setVisible(false);
                    for(int j=0;j<znalezione;j++)
                    {
                        DefaultListModel model = (DefaultListModel)lista.getModel();
                        model.add(j,wynik[j]);
                    }

                    lista.setVisible(true);
                    dalej.setVisible(true);
                }

                nazwaFilmu.setText("");

            }
        });



        dalej = new JButton("Dalej");
        dalej.setBounds(340,400,100,30);
        add(dalej);
        dalej.setVisible(false);

        imie = new JLabel("Podaj imię: ");
        imie.setBounds(220,120,100,30);
        add(imie);
        imie.setVisible(false);

        nazwisko = new JLabel("Podaj nazwisko: ");
        nazwisko.setBounds(220,160,100,30);
        add(nazwisko);
        nazwisko.setVisible(false);

        nrDowodu = new JLabel("Podaj numer dowodu osobistego: ");
        nrDowodu.setBounds(220,200,200,30);
        add(nrDowodu);
        nrDowodu.setVisible(false);

        wpiszImie = new JTextField("");
        wpiszImie.setBounds(290,120,200,30);
        add(wpiszImie);
        wpiszImie.setVisible(false);

        wpiszNazwisko = new JTextField("");
        wpiszNazwisko.setBounds(320,160,200,30);
        add(wpiszNazwisko);
        wpiszNazwisko.setVisible(false);

        wpiszNrDowodu = new JTextField("");
        wpiszNrDowodu.setBounds(420,200,200,30);
        add(wpiszNrDowodu);
        wpiszNrDowodu.setVisible(false);

        dodanie = new JButton("Rezerwuj");
        dodanie.setBounds(220,280,100,30);
        add(dodanie);
        dodanie.setVisible(false);

        wypozycz = new JButton("Wypożycz");
        wypozycz.setBounds(220,320,100,30);
        add(wypozycz);
        wypozycz.setVisible(false);

        dalej.addActionListener(new Akcja()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int index1 = lista.getSelectedIndex();

                if(index1 == (-1))
                {
                    JOptionPane.showMessageDialog(null,"Nie wybrałeś żadnego filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    zmiennaGlobal = (String)model.elementAt(index1);
                    lista.setVisible(false);
                    dalej.setVisible(false);
                    imie.setVisible(true);
                    nazwisko.setVisible(true);
                    nrDowodu.setVisible(true);
                    wpiszImie.setVisible(true);
                    wpiszNazwisko.setVisible(true);
                    wpiszNrDowodu.setVisible(true);
                    dodanie.setVisible(true);
                    wypozycz.setVisible(true);
                }
            }
        });

        dodanie.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String[] tmp;
                tmp = wyszukiwanie(zmiennaGlobal,"rezerwacje.txt");

                String[] tmp1;
                tmp1 = wyszukiwanie(zmiennaGlobal,"wypozyczenia.txt");
                znalezione = 0;

                int dlugosc = zmiennaGlobal.length();

                String zmienna1 = wpiszImie.getText();
                String zmienna2 = wpiszNazwisko.getText();
                String zmienna3 = wpiszNrDowodu.getText();

                if(zmienna1.equals("") || zmienna2.equals("") || zmienna3.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Musisz uzupełnić wszystkie pola!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(tmp[0] == null)
                {
                    if(tmp1[0] != null && tmp1[0].substring(0, dlugosc).equals(zmiennaGlobal))
                    {
                        JOptionPane.showMessageDialog(null,"Film jest wypożyczony!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        imie.setVisible(false);
                        nazwisko.setVisible(false);
                        nrDowodu.setVisible(false);
                        wpiszImie.setVisible(false);
                        wpiszNazwisko.setVisible(false);
                        wpiszNrDowodu.setVisible(false);
                        dodanie.setVisible(false);
                        wypozycz.setVisible(false);
                    }
                    else
                    {
                        try
                        {
                            FileWriter file = new FileWriter("rezerwacje.txt", true);
                            file.append(zmiennaGlobal);
                            file.append(" - "+zmienna1+" "+zmienna2+" - "+zmienna3);
                            file.append("\r\n");
                            file.close();

                            FileWriter file1 = new FileWriter("usuwanie.txt", true);
                            file1.append(zmiennaGlobal);
                            file1.append("\r\n");
                            file1.close();
                        }
                        catch(IOException ex)
                        {}

                        JOptionPane.showMessageDialog(null,"Zarezerwowano film","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        imie.setVisible(false);
                        nazwisko.setVisible(false);
                        nrDowodu.setVisible(false);
                        wpiszImie.setVisible(false);
                        wpiszNazwisko.setVisible(false);
                        wpiszNrDowodu.setVisible(false);
                        dodanie.setVisible(false);
                        wypozycz.setVisible(false);
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Film jest zarezerwowany!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                    imie.setVisible(false);
                    nazwisko.setVisible(false);
                    nrDowodu.setVisible(false);
                    wpiszImie.setVisible(false);
                    wpiszNazwisko.setVisible(false);
                    wpiszNrDowodu.setVisible(false);
                    dodanie.setVisible(false);
                    wypozycz.setVisible(false);
                }
                wpiszImie.setText("");
                wpiszNazwisko.setText("");
                wpiszNrDowodu.setText("");
            }
        });



        wypozycz.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String[] tmp;
                tmp = wyszukiwanie(zmiennaGlobal,"wypozyczenia.txt");

                String[] tmp1;
                tmp1 = wyszukiwanie(zmiennaGlobal,"rezerwacje.txt");
                znalezione = 0;

                int dlugosc = zmiennaGlobal.length();

                String imieGlobal = wpiszImie.getText();
                String nazwiskoGlobal = wpiszNazwisko.getText();
                String nrGlobal = wpiszNrDowodu.getText();


                if(imieGlobal.equals("") || nazwiskoGlobal.equals("") || nrGlobal.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Musisz uzupełnić wszystkie pola!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(tmp[0] == null)
                {
                    if(tmp1[0] != null && tmp1[0].substring(0, dlugosc).equals(zmiennaGlobal))
                    {
                        JOptionPane.showMessageDialog(null,"Film jest zarezerwowany!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        imie.setVisible(false);
                        nazwisko.setVisible(false);
                        nrDowodu.setVisible(false);
                        wpiszImie.setVisible(false);
                        wpiszNazwisko.setVisible(false);
                        wpiszNrDowodu.setVisible(false);
                        dodanie.setVisible(false);
                        wypozycz.setVisible(false);
                    }
                    else
                    {
                        try
                        {
                            FileWriter file = new FileWriter("wypozyczenia.txt", true);
                            file.append(zmiennaGlobal);
                            file.append(" - "+imieGlobal+" "+nazwiskoGlobal+" - "+nrGlobal);
                            file.append("\r\n");

                            FileWriter file1 = new FileWriter("usuwanie.txt", true);
                            file1.append(zmiennaGlobal);
                            file1.append("\r\n");
                            file1.close();

                            file.close();
                        }
                        catch(IOException ex)
                        {}

                        JOptionPane.showMessageDialog(null,"Wypożyczono film","Informacja",JOptionPane.INFORMATION_MESSAGE);
                        imie.setVisible(false);
                        nazwisko.setVisible(false);
                        nrDowodu.setVisible(false);
                        wpiszImie.setVisible(false);
                        wpiszNazwisko.setVisible(false);
                        wpiszNrDowodu.setVisible(false);
                        dodanie.setVisible(false);
                        wypozycz.setVisible(false);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Film jest wypożyczony!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                    imie.setVisible(false);
                    nazwisko.setVisible(false);
                    nrDowodu.setVisible(false);
                    wpiszImie.setVisible(false);
                    wpiszNazwisko.setVisible(false);
                    wpiszNrDowodu.setVisible(false);
                    dodanie.setVisible(false);
                    wypozycz.setVisible(false);
                }


                wpiszImie.setText("");
                wpiszNazwisko.setText("");
                wpiszNrDowodu.setText("");
                model.removeAllElements();

            }
        });


        zwrot = new JButton("Zwrot filmu");
        zwrot.setBounds(15,300,160,60);
        add(zwrot);

        zwrotEtykieta = new JLabel("Wybierz film do zwrotu:");
        zwrotEtykieta.setBounds(315,30,150,30);
        add(zwrotEtykieta);
        zwrotEtykieta.setVisible(false);

        zwrotAction = new JButton("Zwróć!");
        zwrotAction.setBounds(340,465,100,30);
        add(zwrotAction);
        zwrotAction.setVisible(false);

        zwrot.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int ile = ileLini("wypozyczenia.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak filmów do zwrotu!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    zwrotEtykieta.setVisible(true);
                    zwrotAction.setVisible(true);
                    ukryj();
                    String[] wynik;
                    wynik = odczytFilmow("wypozyczenia.txt");

                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelZwrot = (DefaultListModel)listaZwrot.getModel();
                        modelZwrot.add(j,wynik[j]);
                    }
                    listaZwrot.setVisible(true);
                }
            }
        });

        zwrotAction.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int i = listaZwrot.getSelectedIndex();

                if(i == -1)
                {
                    JOptionPane.showMessageDialog(null,"Nie wybrałeś filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    zmiennaGlobal = (String)modelZwrot.elementAt(i);

                    int liczba = ileLini("wypozyczenia.txt");
                    int licznik = ileLini("usuwanie.txt");

                    modelZwrot.remove(i);

                    String[] nowaTablica = new String[liczba-1];
                    String[] tablica = new String[licznik-1];

                    for(int j=0;j<modelZwrot.size();j++)
                    {
                        nowaTablica[j] = (String)modelZwrot.elementAt(j);
                    }

                    for(int j=0;j<modelZwrot.size();j++)
                    {
                        tablica[j] = (String)modelZwrot.elementAt(j);
                    }

                    try
                    {
                        PrintWriter file = new PrintWriter("wypozyczenia.txt");
                        FileWriter file1 = new FileWriter("wypozyczenia.txt",true);

                        file.print("");

                        for(String a: nowaTablica)
                        {
                            file1.append(a);
                            file1.append("\r\n");
                        }

                        file.close();
                        file1.close();
                    }
                    catch(IOException ex)
                    {}

                    try
                    {
                        PrintWriter file = new PrintWriter("usuwanie.txt");
                        FileWriter file1 = new FileWriter("usuwanie.txt",true);

                        file.print("");

                        for(String a: tablica)
                        {
                            file1.append(a);
                            file1.append("\r\n");
                        }

                        file.close();
                        file1.close();
                    }
                    catch(IOException ex)
                    {}

                    JOptionPane.showMessageDialog(null,"Zwrócono film!","Informacja",JOptionPane.INFORMATION_MESSAGE);

                    listaZwrot.setVisible(false);
                    zwrotEtykieta.setVisible(false);
                    zwrotAction.setVisible(false);

                }
            }
        });

        Rezerwacje = new JButton("Zarezerwowane filmy");
        Rezerwacje.setBounds(15,390,160,60);
        add(Rezerwacje);

        rezerwacjaWypozycz = new JButton("Wypożycz!");
        rezerwacjaWypozycz.setBounds(340,465,100,30);
        add(rezerwacjaWypozycz);
        rezerwacjaWypozycz.setVisible(false);

        Rezerwacje.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {



                int ile = ileLini("rezerwacje.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak rezerwacji!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    rezerwacjaWypozycz.setVisible(true);
                    ukryj();
                    String[] wynik;
                    wynik = odczytFilmow("rezerwacje.txt");

                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelRezerwacji = (DefaultListModel)listaRezerwacje.getModel();
                        modelRezerwacji.add(j,wynik[j]);
                    }
                    listaRezerwacje.setVisible(true);
                }
            }
        });

        wypozyczFilm = new JButton("Wypoz!");
        wypozyczFilm.setBounds(340,465,100,30);
        add(wypozyczFilm);
        wypozyczFilm.setVisible(false);

        rezerwacjaWypozycz.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int index = listaRezerwacje.getSelectedIndex();

                if(index == -1)
                {
                    JOptionPane.showMessageDialog(null,"Nie wybrałeś filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    Object[] opcje = { "Tak", "Nie"};
                    int wybrana = JOptionPane.showOptionDialog(null,"Czy na pewno chcesz wypożyczyć ten film?", "Rezerwacja",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opcje, opcje[1]);

                    if(wybrana == 0)
                    {

                        zmiennaGlobal = (String)modelRezerwacji.elementAt(index);

                        int liczba = ileLini("rezerwacje.txt");
                        int licznik = ileLini("wypozyczenia.txt");

                        modelRezerwacji.remove(index);

                        String[] nowaTablica = new String[liczba-1];
                        String[] tablica = new String[licznik+1];

                        for(int j=0;j<modelRezerwacji.size();j++)
                        {
                            nowaTablica[j] = (String)modelRezerwacji.elementAt(j);
                        }

                        for(int j=0;j<modelRezerwacji.size();j++)
                        {
                            tablica[j] = (String)modelRezerwacji.elementAt(j);
                        }

                        try
                        {
                            PrintWriter file = new PrintWriter("rezerwacje.txt");
                            FileWriter file1 = new FileWriter("rezerwacje.txt",true);

                            file.print("");

                            for(String a: nowaTablica)
                            {
                                file1.append(a);
                                file1.append("\r\n");
                            }

                            file.close();
                            file1.close();
                        }
                        catch(IOException ex)
                        {}

                        try
                        {
                            FileWriter file = new FileWriter("wypozyczenia.txt", true);
                            file.append(zmiennaGlobal);
                            file.append("\r\n");

                            file.close();
                        }
                        catch(IOException ex)
                        {}

                        JOptionPane.showMessageDialog(null,"Wypożyczono film!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        wypozyczFilm.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {

            }
        });

        Wypozyczenia = new JButton("Wypożyczone filmy");
        Wypozyczenia.setBounds(620,300,160,60);
        add(Wypozyczenia);

        Wypozyczenia.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int ile = ileLini("wypozyczenia.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak wypożyczeń!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    ukryj();
                    String[] wynik;
                    wynik = odczytFilmow("wypozyczenia.txt");
                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelWypozyczenia = (DefaultListModel)listaWypozyczenia.getModel();
                        modelWypozyczenia.add(j,wynik[j]);
                    }


                    listaWypozyczenia.setVisible(true);
                }
            }
        });


        Filmy = new JButton("Lista filmów");
        Filmy.setBounds(15,210,160,60);
        add(Filmy);

        Filmy.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int ile = ileLini("filmy.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak filmów!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    ukryj();

                    String[] wynik;
                    wynik = odczytFilmow("filmy.txt");

                    Arrays.sort(wynik);
                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelLista = (DefaultListModel)listaFilmy.getModel();
                        modelLista.add(j,wynik[j]);
                    }
                    listaFilmy.setVisible(true);
                }


            }
        });

        anuluj = new JButton("Anuluj rezerwacje");
        anuluj.setBounds(620,390,160,60);
        add(anuluj);

        anulujAction = new JButton("Anuluj!");//usuniecie filmu
        anulujAction.setBounds(340,465,100,30);
        add(anulujAction);
        anulujAction.setVisible(false);

        anuluj.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int ile = ileLini("rezerwacje.txt");

                if(ile == 0)
                {
                    JOptionPane.showMessageDialog(null,"Brak filmów do anulowania!","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    anulujEtykieta.setVisible(true);
                    anulujAction.setVisible(true);
                    ukryj();
                    String[] wynik;
                    wynik = odczytFilmow("rezerwacje.txt");

                    for(int j=0;j<ile;j++)
                    {
                        DefaultListModel modelAnuluj = (DefaultListModel)listaAnuluj.getModel();
                        modelAnuluj.add(j,wynik[j]);
                    }
                    listaAnuluj.setVisible(true);
                }
            }
        });

        anulujAction.addActionListener(new Akcja()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int i = listaAnuluj.getSelectedIndex();

                if(i == -1)
                {
                    JOptionPane.showMessageDialog(null,"Nie wybrałeś filmu!","Błąd",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    zmiennaGlobal = (String)modelAnuluj.elementAt(i);

                    int liczba = ileLini("rezerwacje.txt");
                    int licznik = ileLini("usuwanie.txt");

                    modelAnuluj.remove(i);

                    String[] nowaTablica = new String[liczba-1];
                    String[] tablica = new String[licznik-1];

                    for(int j=0;j<modelAnuluj.size();j++)
                    {
                        nowaTablica[j] = (String)modelAnuluj.elementAt(j);
                    }

                    for(int j=0;j<modelAnuluj.size();j++)
                    {
                        tablica[j] = (String)modelAnuluj.elementAt(j);
                    }

                    try
                    {
                        PrintWriter file = new PrintWriter("rezerwacje.txt");
                        FileWriter file1 = new FileWriter("rezerwacje.txt",true);

                        file.print("");

                        for(String a: nowaTablica)
                        {
                            file1.append(a);
                            file1.append("\r\n");
                        }

                        file.close();
                        file1.close();
                    }
                    catch(IOException ex)
                    {}

                    try
                    {
                        PrintWriter file = new PrintWriter("usuwanie.txt");
                        FileWriter file1 = new FileWriter("usuwanie.txt",true);

                        file.print("");

                        for(String a: tablica)
                        {
                            file1.append(a);
                            file1.append("\r\n");
                        }

                        file.close();
                        file1.close();
                    }
                    catch(IOException ex)
                    {}

                    JOptionPane.showMessageDialog(null,"Anulowano rezerwacje","Informacja",JOptionPane.INFORMATION_MESSAGE);

                    listaAnuluj.setVisible(false);
                    anulujEtykieta.setVisible(false);
                    anulujAction.setVisible(false);

                }
            }
        });

        add(panel);
    }

    private class Akcja implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {}
    }
}